package com.covid.model;

import com.covid.entity.Comment;
import com.querydsl.core.Tuple;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
@Setter
@Builder
public class InforObjectDetailDocument {
    private DocumentDTO documentDTO ;
    private Page<Comment> commentDTOS ;
    private List<DocumentDTO> documentSameAs;
}
