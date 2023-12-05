package com.rentalhive.stockManagement.exceptions.handlers;

import com.rentalhive.stockManagement.exceptions.costums.DateValidationException;
import com.rentalhive.stockManagement.exceptions.costums.DefaultException;
import com.rentalhive.stockManagement.exceptions.interfaces.ExceptionHandler;
import org.springframework.http.HttpStatus;

import java.util.List;

public class DefaultExceptionHandler implements ExceptionHandler {

    @Override
    public List<String> handleException(Exception exception) {

        return List.of(exception.getMessage());
    }

    @Override
    public HttpStatus getStatus() {
        return HttpStatus.INTERNAL_SERVER_ERROR;
    }

    @Override
    public String getMessage() {
        return "Some Thinnnnnng went wrong";
    }
}
