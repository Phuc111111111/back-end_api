package com.covid.model.query;

import com.covid.model.enums.DocumentStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DocumentQuery extends CommonQuery {

    Long categoryId;
    Long idCurrentDocument ;
    DocumentStatus status;
}
