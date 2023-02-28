package com.covid.converter;

import com.covid.entity.Document;
import com.covid.model.DocumentDTO;
import com.covid.model.request.DocumentRequestDTO;
import org.mapstruct.Mapper;

@Mapper
public interface DocumentConverter extends CommonConverter<Document, DocumentDTO> {

    Document toEntity(DocumentRequestDTO model);
}
