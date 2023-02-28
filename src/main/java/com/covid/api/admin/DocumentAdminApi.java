package com.covid.api.admin;


import com.covid.exception.CommonException;
import com.covid.model.DocumentDTO;
import com.covid.model.query.DocumentQuery;
import com.covid.model.request.DocumentRequestDTO;
import com.covid.processor.DocumentProcessor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/admin/documents")
public class DocumentAdminApi {

    DocumentProcessor processor;

    public DocumentAdminApi(DocumentProcessor processor) {
        this.processor = processor;
    }

    @GetMapping
    public Page<DocumentDTO> get(DocumentQuery model) {
        Page<DocumentDTO> page = processor.get(model);
        return page;
    }

    @PostMapping
    public void add(@RequestBody @Valid DocumentRequestDTO model) throws CommonException {
          processor.add(model);
    }

    @PutMapping("/{id}")
    public void edit(@RequestBody @Valid DocumentRequestDTO model, @PathVariable Long id) throws CommonException {
        processor.edit(model, id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) throws CommonException {
        processor.delete(id);
    }

    @GetMapping("/{id}")
    public DocumentDTO get(@PathVariable Long id) throws CommonException {
        return processor.get(id);
    }


}
