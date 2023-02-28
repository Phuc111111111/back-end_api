package com.covid.processor;

import com.covid.converter.MessageConverter;
import com.covid.converter.UserConverter;
import com.covid.entity.ConversationUser;
import com.covid.entity.Message;
import com.covid.model.ChatMessageDTO;
import com.covid.model.MessageDTO;
import com.covid.model.query.CommonQuery;
import com.covid.model.request.MessageRequestDTO;
import com.covid.security.service.UserDetailsImpl;
import com.covid.service.ConversationUserService;
import com.covid.service.MessageService;
import com.covid.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class MessageProcessor {


    MessageService messageService;
    UserService userService;
    UserConverter userConverter;
    MessageConverter converter;
    ConversationUserService conversationUserService;

    public MessageProcessor(MessageService messageService,  MessageConverter converter,  UserService userService,
                            ConversationUserService conversationUserService,  UserConverter userConverter) {
        this.messageService = messageService;
        this.converter = converter;
        this.userService = userService;
        this.conversationUserService = conversationUserService;
        this.userConverter = userConverter;

    }

    public Page<MessageDTO> get(Long conversationId, CommonQuery commonQuery) {
        UserDetailsImpl ud = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        updateIsReadMessage(conversationId, ud);
        return messageService.get(conversationId, commonQuery).map(mapper()) ;
    }

    private void updateIsReadMessage(Long conversationId, UserDetailsImpl ud) {
        ConversationUser userIdNot = conversationUserService.findAllByConversationIdAndUserIdNot(conversationId, ud.getId()).get();
        messageService.updateIsRead(conversationId, userIdNot.getUserId(), 1);
    }

    public void add(Long conversationId, MessageRequestDTO model) {
        UserDetailsImpl ud = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        messageService.save(conversationId, model, ud.getId());
        updateIsReadMessage(conversationId, ud);
    }

    public void add(ChatMessageDTO model) {

        messageService.save(model,  model.getUserId());
    }

    private Function<Message, MessageDTO> mapper() {
        return sub -> {
            UserDetailsImpl ud = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            MessageDTO model = converter.toModel(sub);
            model.setCurrentId(ud.getId());
            ConversationUser conversationUser = conversationUserService.findAllByConversationIdAndUserIdNot(model.getConversationId(), model.getUserId()).get();
            model.setUser(userConverter.toModel(userService.findById(conversationUser.getUserId()).get()));
            return model;
        };
    }
}
