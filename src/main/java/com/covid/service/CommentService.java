package com.covid.service;


import com.covid.entity.Comment;
import com.covid.entity.QComment;
import com.covid.entity.QUser;
import com.covid.model.CommentDTO;
import com.covid.model.query.CommonQuery;
import com.covid.model.query.DocumentQuery;
import com.covid.repository.CommentRepository;
import com.covid.security.service.UserDetailsImpl;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQuery;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.*;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.List;

@Service
public class CommentService extends CommonService<Comment, Long, CommentRepository>  {

    private EntityManager entityManager;

    public CommentService(EntityManager entityManager, CommentRepository repo) {
        super(repo);
        this.entityManager = entityManager;
    }

    public Page<Comment> get(CommonQuery model, Long documentId) {
        Pageable pageable = PageRequest.of(model.getPage() - 1, model.getSize());
        JPAQuery<Comment> query = new JPAQuery(entityManager);
        setQueryCondition(query, model, documentId);

        List<Comment> documents = query.limit(pageable.getPageSize()).offset(pageable.getOffset())
                .orderBy(QComment.comment.createdDate.desc()).fetch();
        Long count = query.fetchCount();

        return  new PageImpl<>(documents, pageable, count);
    }

    private void setQueryCondition(JPAQuery query, CommonQuery model, Long documentId) {
        query.select(QComment.comment).from(QComment.comment);

        if (StringUtils.isNotBlank(model.getKeyword())) {
            query.where(QComment.comment.content.contains(model.getKeyword()));
        }
        query.where(QComment.comment.documentId.eq(documentId));
    }

    public void deleteAllByDocumentId(Long documentId) {
        repo.deleteAllByDocumentId(documentId);
    }
}
