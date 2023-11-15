package com.rentalhive.stockManagement.exceptions.handlers;

import java.util.List;

import com.rentalhive.stockManagement.exceptions.costums.DoNotExistsException;

import com.rentalhive.stockManagement.exceptions.interfaces.ExceptionHandler;

public class DoNotExistsExceptionHandler implements ExceptionHandler {

    @Override
    public List<String> handleException(Exception exception) {

        DoNotExistsException DoNotExistsExceptionHandler = (DoNotExistsException) exception;

        return List.of(DoNotExistsExceptionHandler.getError());

    }

    @Override
    public String getMessage() {
        return "This Record Do Not Exist";
    }
}
