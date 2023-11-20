package com.rentalhive.stockManagement.services.Exceptions;

public class UnknownCategoryException extends RuntimeException {
    public UnknownCategoryException(String message) {
        super(message);
    }
}
