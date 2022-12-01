package com.cognologix.banksystem.Exception;

public class CustomerNotFoundException extends RuntimeException{
    public CustomerNotFoundException(String exception){
        super(exception);
    }
}
