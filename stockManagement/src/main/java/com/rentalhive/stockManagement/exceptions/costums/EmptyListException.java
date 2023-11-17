package com.rentalhive.stockManagement.exceptions.costums;

public class EmptyListException extends RuntimeException {

    private final String error;


    public EmptyListException(String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }

    

}
