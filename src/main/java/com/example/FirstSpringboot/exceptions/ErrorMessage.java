package com.example.FirstSpringboot.exceptions;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@Builder
public class ErrorMessage {
    private HttpStatus httpStatus;
    private String message;
}
