package com.covid.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;
import java.util.Date;

@MappedSuperclass
@Getter @Setter
@EntityListeners(AuditingEntityListener.class)
public class BaseDTO {

    @CreatedDate
    private LocalDateTime createdDate;


    @LastModifiedDate
    private LocalDateTime modifiedDate;


    @CreatedBy
    private String createdBy;


    @LastModifiedBy
    private String modifiedBy;
}
