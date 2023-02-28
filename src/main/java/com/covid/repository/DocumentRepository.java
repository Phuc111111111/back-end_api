package com.covid.repository;

import com.covid.entity.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface DocumentRepository extends JpaRepository<Document, Long>, QuerydslPredicateExecutor<Document> {

    boolean existsDocumentByTitle(String title);

    boolean existsDocumentByTitleAndIdNot(String title, Long id);
}
