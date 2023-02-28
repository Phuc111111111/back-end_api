package com.covid.model;


import com.covid.model.enums.DocumentStatus;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


@Getter @Setter
public class CommentDTO extends BaseDTO {

    private Long id;

    private Long userId;
    private Long documentId;
    private String content;
    private DocumentStatus status;
    private UserDTO userDTO ;

}
