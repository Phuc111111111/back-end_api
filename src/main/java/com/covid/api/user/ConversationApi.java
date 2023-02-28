package com.covid.api.user;


import com.covid.exception.CommonException;
import com.covid.model.ConversationUserDTO;
import com.covid.processor.ConversationProcessor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user/conversations")
public class ConversationApi {

    ConversationProcessor processor;

    public ConversationApi(ConversationProcessor processor) {
        this.processor = processor;
    }

    @PostMapping("/doctor/{id}")
    public void addDoctorToConversation(@PathVariable Long id) throws CommonException {
        processor.addDoctorToConversation(id);
    }


}
