package com.covid.service;


import com.covid.entity.Conversation;
import com.covid.repository.ConversationRepository;
import org.springframework.stereotype.Service;

@Service
public class ConversationService extends CommonService<Conversation, Long, ConversationRepository> {

    public ConversationService(ConversationRepository repo) {
        super(repo);
    }
}
