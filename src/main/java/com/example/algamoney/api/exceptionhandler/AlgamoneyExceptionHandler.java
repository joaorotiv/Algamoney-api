package com.example.algamoney.api.exceptionhandler;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@ControllerAdvice
public class AlgamoneyExceptionHandler extends ResponseEntityExceptionHandler {

    @Autowired
    private MessageSource messageSource;

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {
        String userMessage = messageSource.getMessage("invalid.message", null, LocaleContextHolder.getLocale());
        String developerMessage = ex.getCause() != null ? ex.getCause().toString() : ex.toString();
        List<Error> errors = Arrays.asList( new Error(userMessage,developerMessage));
        return handleExceptionInternal(ex, errors, headers, HttpStatus.BAD_REQUEST, request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {

        List<Error> errors = createListErrors(ex.getBindingResult());
        return handleExceptionInternal(ex, errors, headers, HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler({ EmptyResultDataAccessException.class })
    public ResponseEntity<Object> handleEmptyResultDataAcessException(EmptyResultDataAccessException ex ,WebRequest request) {
        String userMessage = messageSource.getMessage("not-found.resource", null, LocaleContextHolder.getLocale());
        String developerMessage = ex.toString();
        List<Error> errors = Arrays.asList( new Error(userMessage,developerMessage));
        return handleExceptionInternal(ex, errors, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler({DataIntegrityViolationException.class })
    public ResponseEntity<Object> handleDataIntegrityViolationException(DataIntegrityViolationException ex, WebRequest request) {
        String userMessage = messageSource.getMessage("operation.not.allowed", null, LocaleContextHolder.getLocale());
        String developerMessage = ExceptionUtils.getRootCauseMessage(ex);
        List<Error> errors = Arrays.asList( new Error(userMessage,developerMessage));
        return handleExceptionInternal(ex, errors, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    private List<Error> createListErrors(BindingResult bindingResult) {
        List<Error> errors = new ArrayList<>();

        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            String userMessage = messageSource.getMessage(fieldError, LocaleContextHolder.getLocale());
            String developerMessage = fieldError.toString();
            errors.add(new Error(userMessage, developerMessage));
        }
        return errors;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Error {

        private String userMessage;
        private String developerMessage;

    }
}
