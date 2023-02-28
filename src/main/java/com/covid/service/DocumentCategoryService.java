package com.covid.service;

import com.covid.entity.DocumentCategory;
import com.covid.entity.QDocumentCategory;
import com.covid.exception.CommonException;
import com.covid.model.query.CommonQuery;
import com.covid.model.request.DocumentCategoryRequestDTO;
import com.covid.repository.DocumentCategoryRepository;
import com.querydsl.jpa.impl.JPAQuery;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Objects;

@Service
public class DocumentCategoryService  extends CommonService<DocumentCategory, Long, DocumentCategoryRepository> {


    private EntityManager entityManager ;

    public DocumentCategoryService(DocumentCategoryRepository repo, EntityManager entityManager) {
        super(repo);
        this.entityManager = entityManager;
    }


    public List<DocumentCategory> findAll() {
        return repo.findAll();
    }

    public List<DocumentCategory> get(CommonQuery commonQuery) {
        JPAQuery<DocumentCategory> query = new JPAQuery<>(entityManager);
        query.select(QDocumentCategory.documentCategory).from(QDocumentCategory.documentCategory);
        if(StringUtils.isNotBlank(commonQuery.getKeyword())) {
            query.where(QDocumentCategory.documentCategory.name.contains(commonQuery.getKeyword()));
        }
        return query.fetch();
    }

    public void update (DocumentCategoryRequestDTO model, Long id) throws CommonException {
        DocumentCategory documentCategory = repo.findById(id).get();
        if(Objects.isNull(documentCategory)){
            throw  new CommonException("notFound.DocumentCategory") ;
        }
        documentCategory.setName(model.getName());
        repo.save(documentCategory) ;
    }

    @Transactional
    public void delete (Long id) throws CommonException {
        DocumentCategory documentCategory = repo.findById(id).get();
        if(Objects.isNull(documentCategory)){
            throw  new CommonException("notFound.DocumentCategory") ;
        }
        repo.delete(documentCategory);
    }

    public boolean existsByName(String name) {
        return  repo.existsByName(name);
    }

    public boolean existsByNameAndIdNot(String name, Long id) {
        return  repo.existsByNameAndIdNot(name, id);
    }
}
