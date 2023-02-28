package com.covid.api.admin;

import com.covid.exception.CommonException;
import com.covid.model.DocumentCategoryDTO;
import com.covid.model.query.CommonQuery;
import com.covid.model.request.DocumentCategoryRequestDTO;
import com.covid.processor.DocumentCategoryProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/admin/document-category")
public class DocumentCategoryApi {


    public DocumentCategoryApi(DocumentCategoryProcessor processor) {
        this.processor = processor;
    }

    private DocumentCategoryProcessor processor;

    @GetMapping
    public List<DocumentCategoryDTO> get(CommonQuery model) {
        return processor.get(model);
    }

    @GetMapping("/{id}")
    public DocumentCategoryDTO findById(@PathVariable Long id) throws CommonException {
        return processor.findById(id);
    }

    @PostMapping
    public void add(@RequestBody @Valid DocumentCategoryRequestDTO model) throws CommonException {
        processor.add(model);
    }

    @PutMapping("/{id}")
    public void edit(@Valid @RequestBody DocumentCategoryRequestDTO model, @PathVariable Long id) throws CommonException {
         processor.update(model, id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) throws CommonException {
        processor.delete(id);
    }
}

