package com.covid.model.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class DocumentCategoryRequestDTO {

    @NotBlank(message = "name.category.required")
    private String name;
}
