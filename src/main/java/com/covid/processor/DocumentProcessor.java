package com.covid.processor;


import com.covid.converter.DocumentConverter;
import com.covid.entity.Document;
import com.covid.exception.CommonException;
import com.covid.model.DocumentDTO;
import com.covid.model.query.DocumentQuery;
import com.covid.model.request.DocumentRequestDTO;
import com.covid.service.CommentService;
import com.covid.service.DocumentService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class DocumentProcessor {

    private DocumentService service;
    private CommentService commentService;
    private DocumentConverter converter;

    public DocumentProcessor(DocumentService service, DocumentConverter converter
             , CommentService commentService) {
        this.service = service;
        this.converter = converter;
        this.commentService = commentService;
    }

    public Page<DocumentDTO> get(DocumentQuery model) {
        return service.get(model).map(converter::toModel);
    }

    public DocumentDTO get(Long id) throws CommonException {
        Document document = service.findById(id).orElse(null);
        if (Objects.isNull(document)) {
            throw new CommonException("not.found");
        }

        return  converter.toModel(document);
    }

    public void add(DocumentRequestDTO model) throws CommonException {
        validateAdd(model);
        service.save(converter.toEntity(model));
    }

    public void edit(DocumentRequestDTO model, Long id) throws CommonException {
        validateEdit(model, id);
        Document document = service.findById(id).orElse(null);
        if (Objects.isNull(document)) {
            throw new CommonException("not.found");
        }
        service.save(model, document);
    }

    public void delete(Long id) throws CommonException {
        Document document = service.findById(id).orElse(null);
        if (Objects.isNull(document)) {
            throw new CommonException("not.found");
        }
        commentService.deleteAllByDocumentId(document.getId());
        service.deleteById(document.getId());
    }


    private void validateEdit(DocumentRequestDTO model, Long id) throws CommonException {
        if (service.existsDocumentByTitleAndIdNot(model.getTitle(), id)) {
            throw new CommonException("title.duplicated");
        }
    }

    private void validateAdd(DocumentRequestDTO model) throws CommonException {
        if (service.existsDocumentByTitle(model.getTitle())) {
            throw new CommonException("title.duplicated");
        }

    }

}
