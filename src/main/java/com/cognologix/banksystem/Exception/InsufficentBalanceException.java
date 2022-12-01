package com.cognologix.banksystem.Exception;

public class InsufficentBalanceException extends RuntimeException{
    public InsufficentBalanceException(String exception){
        super(exception);
    }
}
