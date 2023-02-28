package com.covid.converter;

import com.covid.entity.DocumentCategory;
import com.covid.model.DocumentCategoryDTO;
import com.covid.model.request.DocumentCategoryRequestDTO;
import org.mapstruct.Mapper;

@Mapper
public interface DocumentCategoryConverter extends CommonConverter<DocumentCategory, DocumentCategoryDTO> {

    DocumentCategory toEntity(DocumentCategoryRequestDTO model);
}
