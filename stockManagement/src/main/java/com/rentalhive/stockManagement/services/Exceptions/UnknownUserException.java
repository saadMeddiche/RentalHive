package com.rentalhive.stockManagement.services.Exceptions;

public class UnknownUserException extends RuntimeException {
    public UnknownUserException(String message) {
        super(message);
    }
}