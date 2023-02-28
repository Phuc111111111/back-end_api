package com.covid.model.request;


import com.covid.model.enums.DocumentStatus;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter @Setter
public class DocumentRequestDTO {

    @NotBlank(message = "title.required")
    private String title;

    private Long categoryId;


    @NotBlank(message = "content.required")
    private String content;

    @NotBlank(message = "image.required")
    private String image;

    private DocumentStatus status;

}
