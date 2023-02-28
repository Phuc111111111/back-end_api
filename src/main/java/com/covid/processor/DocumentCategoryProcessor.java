package com.covid.processor;

import com.covid.converter.DocumentCategoryConverter;
import com.covid.entity.DocumentCategory;
import com.covid.exception.CommonException;
import com.covid.model.DocumentCategoryDTO;
import com.covid.model.query.CommonQuery;
import com.covid.model.request.DocumentCategoryRequestDTO;
import com.covid.service.DocumentCategoryService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


@Component
public class DocumentCategoryProcessor {

    private DocumentCategoryService service ;
    private DocumentCategoryConverter converter ;

    public DocumentCategoryProcessor(DocumentCategoryService documentCategoryService,
                                     DocumentCategoryConverter converter) {
        this.service = documentCategoryService;
        this.converter = converter;
    }

    public void add(DocumentCategoryRequestDTO model) throws CommonException {
        validateAdd(model);
        service.save(converter.toEntity(model)) ;
    }

    public DocumentCategoryDTO findById (Long id) throws CommonException {
        DocumentCategory documentCategory = service.findById(id).orElse(null);
        if (Objects.isNull(documentCategory)) {
            throw new CommonException("not.found");
        }
        return converter.toModel(documentCategory);
    }

    public void update (DocumentCategoryRequestDTO model, Long id) throws CommonException {
        validateEdit(model, id);
        service.update(model, id);
    }

    public void delete(Long id) throws CommonException {
        service.delete(id);
    }

    public List<DocumentCategoryDTO> get(CommonQuery commonQuery) {
        return service.get(commonQuery).stream().map(item -> converter.toModel(item)).collect(Collectors.toList());
    }

    public List<DocumentCategoryDTO> findAll() {
        return service.findAll().stream().map(item -> converter.toModel(item)).collect(Collectors.toList());
    }


    public void validateAdd(DocumentCategoryRequestDTO model) throws CommonException {
        if(service.existsByName(model.getName())) {
            throw new  CommonException("exist.name.of.documentCategory") ;
        }
    }

    public void validateEdit(DocumentCategoryRequestDTO model, Long id) throws CommonException {
        if(service.existsByNameAndIdNot(model.getName(), id)) {
            throw new  CommonException("exist.name.of.documentCategory") ;
        }
    }

}
