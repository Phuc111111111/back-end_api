package com.covid.api.user;


import com.covid.model.DocumentCategoryDTO;
import com.covid.model.query.CommonQuery;
import com.covid.processor.DocumentCategoryProcessor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/user/document-category")
public class DocumentCategoryUserApi {

    public DocumentCategoryUserApi(DocumentCategoryProcessor processor) {
        this.processor = processor;
    }

    private DocumentCategoryProcessor processor;

    @GetMapping
    public List<DocumentCategoryDTO> get(CommonQuery model) {
        return processor.get(model);
    }

}
