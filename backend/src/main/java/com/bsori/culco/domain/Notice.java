package com.bsori.culco.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.OffsetDateTime;
import java.util.Set;

@Entity
@Table(name = "Notice")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public class Notice {

    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer seqId;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, columnDefinition = "longtext") // longtext: 긴 내용도 저장 가능

    private String content;

    @Column(columnDefinition = "tinyint", length = 1) // tinyint + 길이1: Boolean 값(0 또는 1)을 저장하기 위함
    private Boolean isPopup; // 공지사항이 팝업으로 표시될지 여부

    @Column(columnDefinition = "tinyint", length = 1)
    private Boolean isMust; // 공지사항이 필수 읽기인지 여부

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private OffsetDateTime dateCreated;

    @LastModifiedDate
    @Column(nullable = false)
    private OffsetDateTime lastUpdated;

    @Column
    private Integer mFId;

    // 테이블간 관계
    @OneToMany(mappedBy = "notice")
    private Set<NoticeFile> noticeFiles;

    @ManyToOne(fetch = FetchType.EAGER) // 즉시 연관된 엔티티를 함께 로딩, 연관된 엔티티가 항상 필요한 경우 사용 ex) 게시글과 작성자
    @JoinColumn(name = "user_id")
    private User user;
}
