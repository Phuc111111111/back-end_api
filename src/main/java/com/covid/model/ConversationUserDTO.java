package com.covid.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


@Getter @Setter
public class ConversationUserDTO extends BaseDTO {

    private Long id;

    private Long userId;
    private Long conversationId;
    private String content;

    private UserDTO user;
    private Integer isRead;

}
