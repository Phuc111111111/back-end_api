package com.covid.model.enums;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Getter
public enum UserRole {
    ADMIN,
    USER,
    DOCTOR;

}
