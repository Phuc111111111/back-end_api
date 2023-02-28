package com.covid.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table
@Getter @Setter
public class Message extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;
    private Long conversationId;
    private String content;
    private Integer isRead;

}
