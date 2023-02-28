package com.covid.entity;

import com.covid.model.enums.UserRole;
import com.covid.model.enums.UserStatus;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import lombok.experimental.FieldNameConstants;

import javax.persistence.*;

@Entity
@Table(name = "[user]")
@Getter @Setter
@FieldNameConstants
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;
    private String fullName;
    private String phone;
    private String address;
    private String email;
    private String description;
    private String image;

    @Enumerated(EnumType.STRING)
    private UserStatus status;

    @Enumerated(EnumType.STRING)
    private UserRole role;

}
