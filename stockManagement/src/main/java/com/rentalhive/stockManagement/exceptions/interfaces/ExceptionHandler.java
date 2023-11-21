package com.rentalhive.stockManagement.exceptions.interfaces;

import java.util.List;

import org.springframework.http.HttpStatus;

public interface ExceptionHandler {

    public List<String> handleException(Exception exception);

    public HttpStatus getStatus();

    public String getMessage();
}
