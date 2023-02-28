package com.covid.config;

import com.covid.exception.CommonException;
import com.covid.model.ErrorModel;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.net.BindException;
import java.util.Locale;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@ControllerAdvice
public class ExceptionHandlerAdviceConfig {

    private final MessageSource messageSource;

    public ExceptionHandlerAdviceConfig(MessageSource messageSource) {
        this.messageSource = messageSource;
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(BAD_REQUEST)
    public @ResponseBody ErrorModel methodArgumentNotValid(MethodArgumentNotValidException    e, Locale locale) {
        String message = null;
        String field = null;
        FieldError fieldError = e.getBindingResult().getFieldError();
        if (fieldError != null) {
            message = fieldError.getDefaultMessage();
            field = fieldError.getField();
        }

        return ofErrorModel(BAD_REQUEST.value(), field, getTextMessage(message, locale));
    }

    @ExceptionHandler(CommonException.class)
    @ResponseStatus(INTERNAL_SERVER_ERROR)
    public @ResponseBody ErrorModel commonException(Exception e, Locale locale) {
        return ofErrorModel(e.hashCode(), null,  getTextMessage(e.getMessage(), locale));
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(INTERNAL_SERVER_ERROR)
    public @ResponseBody ErrorModel internalChecked(Exception e, Locale locale) {
        return ofErrorModel(INTERNAL_SERVER_ERROR.value(), null,  getTextMessage("internal.error", locale));
    }

    private static ErrorModel ofErrorModel(Integer code, String error, String message) {
        return ErrorModel.of(String.valueOf(code), error, message);
    }

    private String getTextMessage(String messageCode, Locale locale) {
        try {
            return messageSource.getMessage(messageCode, null, locale);
        } catch (NoSuchMessageException e) {
            return messageCode;
        }
    }
}
