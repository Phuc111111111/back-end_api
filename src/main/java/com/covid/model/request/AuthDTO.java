package com.covid.model.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class AuthDTO {

    @NotBlank(message = "username.required")
    private String username;

    @NotBlank(message = "password.required")
    private String password;
}
