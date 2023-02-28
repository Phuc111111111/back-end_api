package com.covid.api.user;


import com.covid.model.ConversationUserDTO;
import com.covid.processor.ConversationUserProcessor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/user/conversation-users")
public class ConversationUserApi {

    ConversationUserProcessor processor;

    public ConversationUserApi(ConversationUserProcessor processor) {
        this.processor = processor;
    }

    @GetMapping
    public List<ConversationUserDTO> get() {
        return processor.get();
    }
}
