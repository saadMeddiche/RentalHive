package com.rentalhive.stockManagement.exceptions.handlers;

import java.util.List;

import com.rentalhive.stockManagement.exceptions.costums.ValidationException;
import com.rentalhive.stockManagement.exceptions.interfaces.ExceptionHandler;

public class ValidationExceptionHandler implements ExceptionHandler {

    @Override
    public List<String> handleException(Exception exception) {

        ValidationException validationException = (ValidationException) exception;

        return validationException.getErrors();
    }

    @Override
    public String getMessage() {
        return "Some Thing Went Wrong With The Validation";
    }

}
