package com.jmockit.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = ResourceNotFoundException.class)
    public ResponseEntity<?> exception(ResourceNotFoundException exception)
    {
        return new ResponseEntity<>("Employee Not Found", HttpStatus.NOT_FOUND);
    }
}
