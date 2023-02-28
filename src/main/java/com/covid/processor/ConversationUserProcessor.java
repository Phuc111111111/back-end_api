package com.covid.processor;


import com.covid.converter.ConversationUserConverter;
import com.covid.converter.UserConverter;
import com.covid.entity.ConversationUser;
import com.covid.model.ConversationUserDTO;
import com.covid.security.service.UserDetailsImpl;
import com.covid.service.ConversationUserService;
import com.covid.service.MessageService;
import com.covid.service.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class ConversationUserProcessor {

    ConversationUserService service;
    UserService userService;
    UserConverter userConverter;
    ConversationUserConverter converter;
    MessageService messageService;

    public ConversationUserProcessor(ConversationUserConverter converter, UserConverter userConverter, UserService userService,
                                     ConversationUserService service, MessageService messageService) {
        this.converter = converter;
        this.userConverter = userConverter;
        this.userService = userService;
        this.service = service;
        this.messageService = messageService;
    }


    public List<ConversationUserDTO> get() {
        UserDetailsImpl ud = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return  service.findAllByUserId(ud.getId())
                .stream().map(mapper()).collect(Collectors.toList());
    }

    private Function<ConversationUser, ConversationUserDTO> mapper() {
        return sub -> {
            ConversationUserDTO model = converter.toModel(sub);
            ConversationUser conversationUser = service.findAllByConversationIdAndUserIdNot(model.getConversationId(), model.getUserId()).get();
            model.setUser(userConverter.toModel(userService.findById(conversationUser.getUserId()).get()));
            Long isRead = messageService.countAllByConversationIdAndUserIdAndIsRead(model.getConversationId(), conversationUser.getUserId(), 0);
            model.setIsRead(isRead > 0l ? 1 : 0);
            return model;
        };
    }
}
