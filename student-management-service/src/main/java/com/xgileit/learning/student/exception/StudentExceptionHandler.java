package com.xgileit.learning.student.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class StudentExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(value = ResourceNotFoundException.class)
    public ResponseEntity<?> exception(ResourceNotFoundException exception)
    {
        return new ResponseEntity<>("Student with student no not found", HttpStatus.NOT_FOUND);
    }
}
