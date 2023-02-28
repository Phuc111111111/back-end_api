package com.covid.model.request;


import com.covid.model.enums.UserRole;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter @Setter
public class AddUserDTO {

    @NotBlank(message = "username.required")
    private String username;

    @NotBlank(message = "password.required")
    private String password;

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

    private UserRole role;

}
