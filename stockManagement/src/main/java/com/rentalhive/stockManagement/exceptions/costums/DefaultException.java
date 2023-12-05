package com.rentalhive.stockManagement.exceptions.costums;

public class DefaultException extends RuntimeException {
    private final String error;


    public DefaultException(String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }
}
