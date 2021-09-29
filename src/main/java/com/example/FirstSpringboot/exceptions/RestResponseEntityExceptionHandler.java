package com.example.FirstSpringboot.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@RestControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
    private HttpHeaders getHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }

    private ResponseEntity<Object> handleExceptionInternal(Exception e, ErrorMessage errorMessage, WebRequest request){
        return handleExceptionInternal(e, errorMessage, getHeaders(), errorMessage.getHttpStatus(), request);
    }

    @ExceptionHandler(value = {EmailTakenException.class})
    protected ResponseEntity<Object> handle(EmailTakenException e, WebRequest request){
        log.info(e.getMessage(), e);

        ErrorMessage errorMessage = new ErrorMessage(HttpStatus.CONFLICT, e.getMessage());

        return handleExceptionInternal(e, errorMessage, request);
    }

    @ExceptionHandler(value = {StudentIdNotExistsException.class})
    protected ResponseEntity<Object> handle(StudentIdNotExistsException e, WebRequest request){
        log.info(e.getMessage(), e);

        ErrorMessage errorMessage = new ErrorMessage(HttpStatus.NOT_FOUND, e.getMessage());

        return handleExceptionInternal(e, errorMessage, request);
    }
}
