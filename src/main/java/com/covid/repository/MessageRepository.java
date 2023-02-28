package com.covid.repository;

import com.covid.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {

    List<Message> findAllByConversationId(Long conversationId);

    @Transactional
    @Modifying
    @Query(value="update Message set isRead = ?3 where conversationId = ?1 and userId = ?2")
    void updateIsRead(Long conversationId, Long userId, Integer isRead);

    Long countAllByConversationIdAndUserIdAndIsRead(Long conversationId, Long userId, Integer isRead);
}
