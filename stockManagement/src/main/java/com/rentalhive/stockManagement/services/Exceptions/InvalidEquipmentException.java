package com.rentalhive.stockManagement.services.Exceptions;

public class InvalidEquipmentException extends RuntimeException {
    public InvalidEquipmentException(String message) {
        super(message);
    }
}
