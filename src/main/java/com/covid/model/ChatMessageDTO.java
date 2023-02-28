package com.covid.model;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ChatMessageDTO {

    private MessageType type;
    private String content;
    private Long conversationId;
    private Long userId;
    private String username;


    @Getter
    public enum MessageType {
        CHAT, JOIN, LEAVE
    }
}
