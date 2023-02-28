package com.covid.service;


import com.covid.entity.*;
import com.covid.model.query.CommonQuery;
import com.covid.model.query.DocumentQuery;
import com.covid.model.request.DocumentRequestDTO;
import com.covid.repository.DocumentCategoryRepository;
import com.covid.repository.DocumentRepository;
import com.querydsl.jpa.impl.JPAQuery;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Objects;

@Service
public class DocumentService extends CommonService<Document, Long, DocumentRepository> {

    private EntityManager entityManager;


    public DocumentService(EntityManager entityManager, DocumentRepository repo) {
        super(repo);
        this.entityManager = entityManager;
    }

    public List<Document> getListDocumentInCategory(Long idCate) {
        JPAQuery<Document> query = new JPAQuery<>(entityManager);
        query.select(QDocument.document).from(QDocument.document).limit(7).orderBy(QDocument.document.createdDate.desc()).
                where(QDocument.document.categoryId.eq(idCate));

        return query.fetch();
    }

    public Page<Document> get(DocumentQuery model) {
        Pageable pageable = PageRequest.of(model.getPage() - 1, model.getSize());
        JPAQuery<Document> query = new JPAQuery(entityManager);
        setQueryCondition(query, model);

        List<Document> documents = query.limit(pageable.getPageSize()).offset(pageable.getOffset())
                    .orderBy(QDocument.document.createdDate.desc()).fetch();
        Long count = query.fetchCount();

        return  new PageImpl<>(documents, pageable, count);
    }

    private void setQueryCondition(JPAQuery query, DocumentQuery model) {
        query.select(QDocument.document).from(QDocument.document);

        if (Objects.nonNull(model.getCategoryId())) {
            query.where(QDocument.document.categoryId.eq(model.getCategoryId()));
        }
        if (Objects.nonNull(model.getIdCurrentDocument())){
            query.where(QDocument.document.id.ne(model.getIdCurrentDocument()));
        }
        if (StringUtils.isNotBlank(model.getKeyword())) {
            query.where(QDocument.document.title.contains(model.getKeyword()));
        }
        if (Objects.nonNull(model.getStatus())) {
            query.where(QDocument.document.status.eq(model.getStatus()));
        }
    }

    public void save(DocumentRequestDTO model, Document document) {
        document.setImage(model.getImage());
        document.setTitle(model.getTitle());
        document.setCategoryId(model.getCategoryId());
        document.setContent(model.getContent());

        repo.save(document);
    }

    public boolean existsDocumentByTitle(String title) {
        return repo.existsDocumentByTitle(title);
    }

    public boolean existsDocumentByTitleAndIdNot(String title, Long id) {
        return repo.existsDocumentByTitleAndIdNot(title, id);
    }

}
