package com.covid.service;


import com.covid.entity.Message;
import com.covid.entity.QMessage;
import com.covid.model.ChatMessageDTO;
import com.covid.model.query.CommonQuery;
import com.covid.model.request.MessageRequestDTO;
import com.covid.repository.MessageRepository;
import com.querydsl.jpa.impl.JPAQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.List;

@Service
public class MessageService extends CommonService<Message, Long, MessageRepository> {

    EntityManager entityManager;

    public MessageService(MessageRepository repo,  EntityManager entityManager) {
        super(repo);
        this.entityManager = entityManager;
    }

    public List<Message> findAllByConversationId(Long conversationId) {
        return repo.findAllByConversationId(conversationId);
    }

    public Page<Message> get(Long conversationId, CommonQuery model) {
        Pageable pageable = PageRequest.of(model.getPage() - 1, model.getSize());
        JPAQuery<Message> query = new JPAQuery(entityManager);

        List<Message> messages = query.select(QMessage.message).from(QMessage.message).limit(pageable.getPageSize()).offset(pageable.getOffset())
                .where(QMessage.message.conversationId.eq(conversationId))
                .orderBy(QMessage.message.createdDate.desc()).fetch();
        Long count = query.fetchCount();

        return  new PageImpl<>(messages, pageable, count);
    }

    public void save(Long conversationId, MessageRequestDTO model, Long userCreate) {
        Message message = new Message();
        message.setContent(model.getContent());
        message.setConversationId(conversationId);
        message.setUserId(userCreate);
        message.setIsRead(0);
        repo.save(message);
    }

    public void save(ChatMessageDTO model, Long userCreate) {
        Message message = new Message();
        message.setContent(model.getContent());
        message.setConversationId(model.getConversationId());
        message.setUserId(userCreate);
        repo.save(message);
    }

    public void updateIsRead(Long conversationId, Long userId, Integer isRead) {
        repo.updateIsRead(conversationId, userId, isRead);
    }

    public Long countAllByConversationIdAndUserIdAndIsRead(Long conversationId, Long userId, Integer isRead) {
        return repo.countAllByConversationIdAndUserIdAndIsRead(conversationId, userId, isRead);
    }
}
