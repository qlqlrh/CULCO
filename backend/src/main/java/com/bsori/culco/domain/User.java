package com.bsori.culco.domain;

import com.bsori.culco.model.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import org.antlr.v4.runtime.misc.NotNull;
import jakarta.validation.constraints.Pattern;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.OffsetDateTime;
import java.util.Set;


@Entity
@Table(name = "Users")
public class User {

    @Id // id를 PK로 지정
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer seqId;

    @Column
    private String name;

    @Column
    private String phone;

    @Column(nullable = false)
    @NotBlank(message = "이메일은 필수 입력값입니다.")
    @Pattern(
            regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$",
            message = "올바르지 않은 이메일 형식입니다."
    )
    private String email;

    @Enumerated(EnumType.STRING)
    @NotNull
    private Role role;

    @Column
    private String status;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private OffsetDateTime dateCreated;

    @LastModifiedDate
    @Column(nullable = false)
    private OffsetDateTime lastUpdated;

    // 테이블간 관계 설정
    @OneToMany(mappedBy = "user")
    private Set<UserFile> userFiles;

    @OneToMany(mappedBy = "users")
    private Set<Mentor> mentors;

    @OneToMany(mappedBy = "user")
    private Set<Notice> notices;


    // 멤버 함수
    public User update(String name, String email) {
        this.name = name;
        this.email = email;

        return this;
    }

    public String getRoleKey() {
        return this.role.getKey();
    }
}
