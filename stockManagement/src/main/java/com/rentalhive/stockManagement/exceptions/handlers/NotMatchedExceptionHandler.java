package com.rentalhive.stockManagement.exceptions.handlers;

import java.util.List;

import org.springframework.http.HttpStatus;

import com.rentalhive.stockManagement.exceptions.costums.NotMatchedException;
import com.rentalhive.stockManagement.exceptions.interfaces.ExceptionHandler;

public class NotMatchedExceptionHandler implements ExceptionHandler {

    @Override
    public List<String> handleException(Exception exception) {

        NotMatchedException notMatchedException = (NotMatchedException) exception;

        return List.of(notMatchedException.getError());

    }

    @Override
    public HttpStatus getStatus() {
        return HttpStatus.BAD_REQUEST;
    }

    @Override
    public String getMessage() {
        return "This Record Already Exists";
    }
}
