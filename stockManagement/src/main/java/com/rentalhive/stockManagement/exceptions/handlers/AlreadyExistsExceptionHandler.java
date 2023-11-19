package com.rentalhive.stockManagement.exceptions.handlers;

import java.util.List;

import org.springframework.http.HttpStatus;

import com.rentalhive.stockManagement.exceptions.costums.AlreadyExistsException;
import com.rentalhive.stockManagement.exceptions.interfaces.ExceptionHandler;

public class AlreadyExistsExceptionHandler implements ExceptionHandler {

    @Override
    public List<String> handleException(Exception exception) {

        AlreadyExistsException alreadyExistsExceptionHandler = (AlreadyExistsException) exception;

        return List.of(alreadyExistsExceptionHandler.getError());

    }

    @Override
    public HttpStatus getStatus() {

        return HttpStatus.CONFLICT;
    }

    @Override
    public String getMessage() {
        return "This Record Already Exists";
    }

}
