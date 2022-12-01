package com.cognologix.banksystem.Exception;

public class EmptyFieldException extends RuntimeException{
    public EmptyFieldException() {
    }

    public EmptyFieldException(String message) {
        super(message);
    }
}
