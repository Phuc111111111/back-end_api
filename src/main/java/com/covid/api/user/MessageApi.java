package com.covid.api.user;


import com.covid.model.MessageDTO;
import com.covid.model.query.CommonQuery;
import com.covid.model.request.MessageRequestDTO;
import com.covid.processor.MessageProcessor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/user/messages")
public class MessageApi {

    MessageProcessor processor;

    public MessageApi(MessageProcessor processor) {
        this.processor = processor;
    }

    @GetMapping("/{conversationId}")
    public Page<MessageDTO> get(@PathVariable Long conversationId, CommonQuery model) {
        return processor.get(conversationId, model);
    }

    @PostMapping("/{conversationId}")
    public void add(@PathVariable Long conversationId, @Valid @RequestBody MessageRequestDTO model) {
         processor.add(conversationId, model);
    }

}
