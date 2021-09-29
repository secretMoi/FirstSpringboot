package com.example.FirstSpringboot.exceptions;

public class StudentIdNotExistsException extends RuntimeException {
    public StudentIdNotExistsException(Long studentId) {
        super("Student with id " + studentId + " does not exists");
    }
}
