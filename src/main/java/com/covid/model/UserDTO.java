package com.covid.model;

import com.covid.model.enums.UserRole;
import com.covid.model.enums.UserStatus;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;


@Getter @Setter
public class UserDTO extends BaseDTO {

    private Long id;

    private String username;
    private String password;
    private String fullName;
    private String phone;
    private String address;
    private String email;
    private String description;
    private String image;
    private UserStatus status;
    private UserRole role;

}
