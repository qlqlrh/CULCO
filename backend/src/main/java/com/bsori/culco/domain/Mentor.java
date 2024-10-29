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
@Table(name = "Mentor")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public class Mentor {

    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer seqId;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String category;

    @Column(nullable = false)
    private Integer minMentee;

    @Column(nullable = false)
    private Integer maxMentee;

    @Column(columnDefinition = "longtext")
    private String content;

    @Column(nullable = false)
    private String team;

    @Column
    private Integer mFId;

    @Column
    private Integer status;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private OffsetDateTime dateCreated;

    @LastModifiedDate
    @Column(nullable = false)
    private OffsetDateTime lastUpdated;

    // 테이블간 관계
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "mentor")
    private Set<MentorFile> mentorFiles;
}
