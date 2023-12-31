package com.rentalhive.stockManagement.exceptions.handlers;

import java.util.List;

import org.springframework.http.HttpStatus;

import com.rentalhive.stockManagement.exceptions.costums.DoNotExistsException;

import com.rentalhive.stockManagement.exceptions.interfaces.ExceptionHandler;

public class DoNotExistsExceptionHandler implements ExceptionHandler {

    @Override
    public List<String> handleException(Exception exception) {

        DoNotExistsException doNotExistsExceptionHandler = (DoNotExistsException) exception;

        return List.of(doNotExistsExceptionHandler.getError());

    }

    @Override
    public HttpStatus getStatus() {
        return HttpStatus.NOT_FOUND;
    }

    @Override
    public String getMessage() {
        return "This Record Do Not Exist";
    }
}
