package com.car.rental.management.exception;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class CarExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> exception(ResourceNotFoundException ex){
        log.error("Resource no found error: {}", ex.getMessage());
        String errorMessage = ex.getLocalizedMessage();
        if(errorMessage == null) errorMessage = ex.toString();
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }
}
