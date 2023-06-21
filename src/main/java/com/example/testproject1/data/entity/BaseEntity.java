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
@MappedSuperclass  // 이 어노테이션은 persistence 에서 자동으로 인식을 하고 필드 변수들을 상속한 엔티티에 포함을 시켜준다.
@EntityListeners(AuditingEntityListener.class)  // 공통된 속성들을 처리해주기 위해서 사용
// AuditingEntityListener 의 @PrePersist, @PreUpdate 가 붙은 어노테이션의 메서드를 통해서
// @CreatedDate, @LastModifiedDate 의 어노테이션에 대한 처리들을 AuditingEntityListener 를 통해서 진행한다.
public class BaseEntity {

    // 날짜 값도 자동으로 어노테이션으로 자동으로 들어감.
    @CreatedDate
    @Column(updatable = false)  // 개발자가 임의로 변경할 수 없게끔 속성 지정
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
