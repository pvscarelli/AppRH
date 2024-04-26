package com.rhapp.rh.domain.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(JobNotFoundException.class)
    private ResponseEntity<Exception> userNotFoundHandler(JobNotFoundException exception){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception);
    }
    
    @ExceptionHandler(ApplicantNotFoundException.class)
    private ResponseEntity<Exception> duplicateCredentialsHandler(ApplicantNotFoundException exception){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception);
    }

    @ExceptionHandler(DuplicatedCredentialsException.class)
    private ResponseEntity<Exception> incorrectCredentialsHandler(DuplicatedCredentialsException exception){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception);
    }
}
