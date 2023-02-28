package com.covid.model.request;


import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class CommentRequestDTO {

    @NotNull
    Long documentId;

    @NotBlank(message = "content.required")
    String content;
}
