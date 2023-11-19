package com.rentalhive.stockManagement.exceptions.handlers;

import java.util.List;

import org.springframework.http.HttpStatus;

import com.rentalhive.stockManagement.exceptions.costums.DoNotExistsException;
import com.rentalhive.stockManagement.exceptions.costums.EmptyListException;
import com.rentalhive.stockManagement.exceptions.interfaces.ExceptionHandler;

public class EmptyListExceptionHandler implements ExceptionHandler {

    @Override
    public List<String> handleException(Exception exception) {

        EmptyListException emptyListException = (EmptyListException) exception;

        return List.of(emptyListException.getError());

    }

    @Override
    public HttpStatus getStatus() {

        return HttpStatus.NO_CONTENT;
    }

    @Override
    public String getMessage() {
        return "The List Is Empty";
    }
}
