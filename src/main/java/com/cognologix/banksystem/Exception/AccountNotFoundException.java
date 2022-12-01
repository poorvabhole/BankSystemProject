package com.cognologix.banksystem.Exception;

public class AccountNotFoundException extends RuntimeException{
    public AccountNotFoundException(String exception){
        super(exception);
    }
}
