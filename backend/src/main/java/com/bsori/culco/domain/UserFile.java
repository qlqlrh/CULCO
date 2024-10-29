package com.bsori.culco.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.OffsetDateTime;

@Entity
@Table(name = "UserFile")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public class UserFile {

    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer seqId;

    @Column
    private String fileSrc;

    @Column
    private String type;

    @Column
    private LocalDate expireDt;

    @Column
    private Integer userSeqId;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private OffsetDateTime dateCreated;

    @LastModifiedDate
    @Column(nullable = false)
    private OffsetDateTime lastUpdated;

    // 테이블간 관계
    @ManyToOne(fetch = FetchType.LAZY) // 실제로 필요할 때 쿼리를 실행, 가끔만 필요하거나 데이터가 클 때 사용 ex) 사용자와 첨부 파일
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
