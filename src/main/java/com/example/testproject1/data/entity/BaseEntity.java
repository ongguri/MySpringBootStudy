package com.example.testproject1.data.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity {

    // 날짜 값도 자동으로 어노테이션으로 자동으로 들어감.
    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createAt;

    /*
    @CreateBy
    @Column(updatable = false)
    private String createBy;
     */

    @LastModifiedDate
    private LocalDateTime updateAt;

    /*
    @LastModifiedBy
    private String updateBy;
     */
}
