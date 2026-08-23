package com.example.bank_management.exception;

public class InsufficientBalanceException extends  RuntimeException{

    public InsufficientBalanceException(String message){
        super(message);
    }
}
