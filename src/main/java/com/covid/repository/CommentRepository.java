package com.covid.repository;

import com.covid.entity.Comment;
import com.querydsl.core.Tuple;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long>,
        QuerydslPredicateExecutor<Comment> , PagingAndSortingRepository<Comment, Long> {

    @Modifying
    @Transactional
    void deleteAllByDocumentId(Long documentId);

}
