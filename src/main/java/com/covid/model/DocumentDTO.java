package com.covid.model;


import com.covid.model.enums.DocumentStatus;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


@Getter @Setter
public class DocumentDTO extends BaseDTO {

    private Long id;

    private String title;
    private Long categoryId;
    private String content;
    private String image;
    private DocumentStatus status;
}
