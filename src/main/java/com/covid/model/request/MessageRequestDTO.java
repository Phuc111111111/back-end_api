package com.covid.model.request;


import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter @Setter
public class MessageRequestDTO {

    @NotBlank(message = "content.required")
    private String content;
}
