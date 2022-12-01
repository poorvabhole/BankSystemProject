package com.cognologix.banksystem.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandler {
    @org.springframework.web.bind.annotation.ExceptionHandler(InsufficentBalanceException.class)
    public ResponseEntity<String> handleInsufficentBalanceException(Exception exception){
        return new ResponseEntity<>("Error : "+exception.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
