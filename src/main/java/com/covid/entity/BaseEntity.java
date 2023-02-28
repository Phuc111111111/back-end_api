package com.covid.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;
import java.util.Date;

@MappedSuperclass
@Getter @Setter
@EntityListeners(AuditingEntityListener.class)
@FieldNameConstants
public class BaseEntity {

    @CreatedDate
    private LocalDateTime createdDate;


    @LastModifiedDate
    private LocalDateTime modifiedDate;


    @CreatedBy
    private String createdBy;


    @LastModifiedBy
    private String modifiedBy;
}
