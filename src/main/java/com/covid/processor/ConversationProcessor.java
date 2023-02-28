package com.covid.processor;

import com.covid.converter.ConversationUserConverter;
import com.covid.converter.UserConverter;
import com.covid.entity.Conversation;
import com.covid.entity.ConversationUser;
import com.covid.entity.User;
import com.covid.exception.CommonException;
import com.covid.model.ConversationUserDTO;
import com.covid.security.service.UserDetailsImpl;
import com.covid.service.ConversationService;
import com.covid.service.ConversationUserService;
import com.covid.service.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class ConversationProcessor {

    ConversationService service;
    ConversationUserService conversationUserService;
    UserService userService;
    ConversationUserConverter converter;
    UserConverter userConverter;


    public ConversationProcessor(ConversationService service, ConversationUserService conversationUserService,
                                 UserService userService, ConversationUserConverter converter,  UserConverter userConverter) {
        this.service = service;
        this.conversationUserService = conversationUserService;
        this.userService = userService;
        this.converter = converter;
        this.userConverter = userConverter;
    }

    public void addDoctorToConversation(Long doctorId) throws CommonException {

        UserDetailsImpl ud = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (!checkExist(doctorId, ud)) {

            if (ud.getId().equals(doctorId)) throw new CommonException("action.not.allowed");
            User userDoctor = userService.findById(doctorId).orElse(null);

            if (Objects.isNull(userDoctor)) throw new CommonException("not.found");

            User userCreate = userService.findById(ud.getId()).orElse(null);

            Conversation conversation = service.save(new Conversation());

            conversationUserService.save(ConversationUser.builder().userId(userDoctor.getId())
                    .conversationId(conversation.getId()).content(userDoctor.getFullName()).build());

            conversationUserService.save(ConversationUser.builder().userId(userCreate.getId())
                    .conversationId(conversation.getId()).content(userCreate.getFullName()).build());
        }
    }

    private boolean checkExist(Long userId, UserDetailsImpl ud) {

        List<ConversationUserDTO> userCurrents = conversationUserService.findAllByUserId(ud.getId())
                .stream().map(converter::toModel).collect(Collectors.toList());
        List<ConversationUserDTO> userConnects = conversationUserService.findAllByUserId(userId)
                .stream().map(converter::toModel).collect(Collectors.toList());;

        for (ConversationUserDTO current : userCurrents) {
            boolean isExít = userConnects.stream().anyMatch(item -> item.getConversationId().equals(current.getConversationId()));
            if (isExít) return true;
        }
        return false;
    }

}
