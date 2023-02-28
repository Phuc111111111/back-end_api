package com.covid.model;


import com.covid.entity.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


@Getter @Setter
public class MessageDTO extends BaseDTO {


    private Long id;

    private Long conversationId;
    private Long userId;
    private String content;
    private Integer isRead;

    private UserDTO user;
    private Long currentId;
}
