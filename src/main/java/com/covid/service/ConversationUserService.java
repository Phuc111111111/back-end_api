package com.covid.service;

import com.covid.entity.ConversationUser;
import com.covid.repository.ConversationUserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ConversationUserService extends CommonService<ConversationUser, Long, ConversationUserRepository> {

    public ConversationUserService(ConversationUserRepository repo) {
        super(repo);
    }


    public List<ConversationUser> findAllByUserId(Long userId) {
        return repo.findAllByUserId(userId);
    }

    public Optional<ConversationUser> findAllByConversationIdAndUserIdNot(Long conversationId, Long userId) {
        return repo.findAllByConversationIdAndUserIdNot(conversationId, userId);
    }
}
