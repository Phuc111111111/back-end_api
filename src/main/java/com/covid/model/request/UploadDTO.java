package com.covid.model.request;


import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter @Setter
public class UploadDTO {
    MultipartFile file;
}
