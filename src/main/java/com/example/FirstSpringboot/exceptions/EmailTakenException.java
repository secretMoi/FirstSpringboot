package com.example.FirstSpringboot.exceptions;

public class EmailTakenException extends RuntimeException {
    public EmailTakenException(String email) {
        super("Email " + email + " already taken");
    }
}
