package com.rentalhive.stockManagement.helpers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.rentalhive.stockManagement.exceptions.factories.ExceptionHandlerFactory;
import com.rentalhive.stockManagement.exceptions.interfaces.ExceptionHandler;

public class ControllerHelper {

    /**
     * Generates a ResponseEntity object depending on the given Exception.
     *
     * @param exception the Exception object to handle
     * @return a ResponseEntity object containing a list of errors and the HTTP
     *         status
     */
    public ResponseEntity<?> getResponseEntityDependingOnException(Exception exception) {

        ExceptionHandler exceptionHandler = ExceptionHandlerFactory.getExceptionHandler(exception);

        List<String> errors = exceptionHandler.handleException(exception);

        HttpStatus status = exceptionHandler.getStatus();

        return new ResponseEntity<>(errors, status);
    }
}
