package com.cognologix.banksystem.Exception;

public class AccountCreationException extends RuntimeException{
    public AccountCreationException(String exception){
        super(exception);
    }
}
