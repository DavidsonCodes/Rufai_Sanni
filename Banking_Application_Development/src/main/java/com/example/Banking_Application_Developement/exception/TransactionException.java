package com.example.Banking_Application_Developement.exception;

public class TransactionException extends RuntimeException{

    private String message;

    public TransactionException(String message) {
        super(message);
    }
}
