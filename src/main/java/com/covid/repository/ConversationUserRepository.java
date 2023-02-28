package com.covid.repository;

import com.covid.entity.ConversationUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ConversationUserRepository extends JpaRepository<ConversationUser, Long> {

    List<ConversationUser> findAllByUserId(Long userId);
    Optional<ConversationUser> findAllByConversationIdAndUserIdNot(Long conversationId, Long userId);
}
