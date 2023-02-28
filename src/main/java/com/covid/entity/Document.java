package com.covid.entity;


import com.covid.model.enums.DocumentStatus;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table
@Getter @Setter
public class Document extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private Long categoryId;
    private String content;
    private String image;
    
    @Enumerated(EnumType.STRING)
    private DocumentStatus status;
}
