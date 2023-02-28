package com.covid.api.user;

import com.covid.exception.CommonException;
import com.covid.model.DocumentDTO;
import com.covid.model.query.DocumentQuery;
import com.covid.processor.DocumentProcessor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/user/documents")
public class DocumentUserApi {

    DocumentProcessor processor;

    public DocumentUserApi(DocumentProcessor processor) {
        this.processor = processor;
    }

    @GetMapping
    public Page<DocumentDTO> get(DocumentQuery model) {
        return processor.get(model);
    }

    @GetMapping("/{id}")
    public DocumentDTO get(@PathVariable Long id) throws CommonException {
        return processor.get(id);
    }

}