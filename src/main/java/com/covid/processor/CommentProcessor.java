package com.covid.processor;


import com.covid.converter.CommentConverter;
import com.covid.converter.UserConverter;
import com.covid.entity.Comment;
import com.covid.entity.User;
import com.covid.model.CommentDTO;
import com.covid.model.UserDTO;
import com.covid.model.query.CommonQuery;
import com.covid.model.request.CommentRequestDTO;
import com.covid.security.service.UserDetailsImpl;
import com.covid.service.CommentService;
import com.covid.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CommentProcessor  {

    CommentService service;
    CommentConverter converter;
    UserConverter userConverter ;
    UserService userService ;

    public CommentProcessor(CommentService service, CommentConverter converter, UserConverter userConverter, UserService userService) {
        this.service = service;
        this.converter = converter;
        this.userConverter = userConverter;
        this.userService = userService;
    }

    public void delete(Long id) {
        service.deleteById(id);
    }

    public Page<CommentDTO> get(CommonQuery model, Long documentId) {
        return service.get(model, documentId).map(item -> {
            CommentDTO commentDTO = converter.toModel(item);
            UserDTO userDTO = userConverter.toModel(userService.findById(item.getUserId()).get()) ;
            commentDTO.setUserDTO(userDTO);
            return commentDTO;
        });
    }

    public void add(CommentRequestDTO model){
        UserDetailsImpl ud = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Comment comment = converter.toEntity(model, ud.getId());
        service.save(comment);
    }

}
