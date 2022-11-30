package com.cognologix.banksystem.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class InsufficentBalanceExceptionHandler {
    @ExceptionHandler(InsufficentBalanceException.class)
    public ResponseEntity<String> handleInsufficentBalanceException(Exception exception){
        return new ResponseEntity<>("Exception : "+exception.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
