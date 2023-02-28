package com.covid.entity;


import lombok.*;

import javax.persistence.*;

@Entity
@Table
@Getter @Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ConversationUser extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;
    private Long conversationId;
    private String content;

}
