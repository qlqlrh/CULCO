package com.bsori.culco.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.OffsetDateTime;

@Entity
@Table(name = "MentorFile")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public class MentorFile {

    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer seqId;

    @Column(nullable = false)
    private String fileSrc;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private OffsetDateTime dateCreated;

    @LastModifiedDate
    @Column(nullable = false)
    private OffsetDateTime lastUpdated;

    // 테이블간 관계
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "mentor_id")
    private Mentor mentor;
}