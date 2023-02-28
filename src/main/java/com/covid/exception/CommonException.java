package com.covid.exception;

import com.covid.model.ErrorModel;
import lombok.Getter;

import java.util.function.Supplier;

public class CommonException extends  Exception {

    @Getter
    private final ErrorModel errorModel;

    public CommonException(String message) {
        super(message);
        errorModel = null;
    }

    protected CommonException(ErrorModel errorModel) {
        super(errorModel.getMessage());
        this.errorModel = errorModel;
    }
}
