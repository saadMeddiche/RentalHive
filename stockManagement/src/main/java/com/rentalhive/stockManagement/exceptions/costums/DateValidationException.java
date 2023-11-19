package com.rentalhive.stockManagement.exceptions.costums;

public class DateValidationException extends RuntimeException {

    private final String error;


    public DateValidationException(String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }

}
