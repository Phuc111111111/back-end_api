package com.covid.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ErrorModel {

    private String code;
    private String error;
    private String message;
    private String description;

    private ErrorModel(String code, String error, String message, String description) {
        this.code = code;
        this.error = error;
        this.message = message;
        this.description = description;
    }

    public static ErrorModel of(String code, String error, String message) {
        return new ErrorModel(code, error, message, null);
    }
}
