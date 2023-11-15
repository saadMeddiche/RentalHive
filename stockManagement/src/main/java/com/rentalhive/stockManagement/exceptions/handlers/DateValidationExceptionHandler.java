package com.rentalhive.stockManagement.exceptions.handlers;

import java.util.List;

import com.rentalhive.stockManagement.exceptions.costums.DateValidationException;
import com.rentalhive.stockManagement.exceptions.interfaces.ExceptionHandler;

public class DateValidationExceptionHandler implements ExceptionHandler {

    @Override
    public List<String> handleException(Exception exception) {

        DateValidationException dateValidationExceptionHandler = (DateValidationException) exception;

        return List.of(dateValidationExceptionHandler.getError());
    }

    @Override
    public String getMessage() {
        return "This Record Already Exists";
    }
}
