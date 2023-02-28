package com.covid.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class InforCategoryDTO {
    private DocumentCategoryDTO documentCategoryDTO ;
    private List<DocumentDTO> documentDTOS ;
}
