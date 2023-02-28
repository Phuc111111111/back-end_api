package com.covid.model.request;


import com.covid.model.enums.UserRole;
import com.covid.model.enums.UserStatus;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter @Setter
public class UpdateUserDTO {

    @NotBlank(message = "fullName.required")
    private String fullName;

    @NotBlank(message = "phone.required")
    private String phone;

    private String address;

    @NotBlank(message = "email.required")
    @Email(message = "email.format.invalid")
    private String email;

    private String description;

    private String image;

    private UserStatus status;
    private UserRole role;

}
