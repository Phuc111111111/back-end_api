package com.covid.converter;

import com.covid.entity.Comment;
import com.covid.model.CommentDTO;
import com.covid.model.query.CommonQuery;
import com.covid.model.request.CommentRequestDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface CommentConverter extends CommonConverter<Comment, CommentDTO> {

    @Mapping(target = "userId", source = "userId")
    Comment toEntity(CommentRequestDTO model, Long userId);
}
