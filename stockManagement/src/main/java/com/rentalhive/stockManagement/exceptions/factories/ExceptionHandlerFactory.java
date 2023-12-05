package com.rentalhive.stockManagement.exceptions.factories;

import com.rentalhive.stockManagement.exceptions.costums.AlreadyExistsException;
import com.rentalhive.stockManagement.exceptions.costums.DateValidationException;
import com.rentalhive.stockManagement.exceptions.costums.DoNotExistsException;
import com.rentalhive.stockManagement.exceptions.costums.EmptyListException;
import com.rentalhive.stockManagement.exceptions.costums.NotMatchedException;
import com.rentalhive.stockManagement.exceptions.costums.ValidationException;
import com.rentalhive.stockManagement.exceptions.handlers.*;
import com.rentalhive.stockManagement.exceptions.interfaces.ExceptionHandler;

public class ExceptionHandlerFactory {

    public static ExceptionHandler getExceptionHandler(Exception exception) {

        if (exception instanceof ValidationException) {
            return new ValidationExceptionHandler();
        }

        if (exception instanceof AlreadyExistsException) {
            return new AlreadyExistsExceptionHandler();
        }

        if (exception instanceof DoNotExistsException) {
            return new DoNotExistsExceptionHandler();
        }

        if (exception instanceof NotMatchedException) {
            return new NotMatchedExceptionHandler();
        }

        if (exception instanceof DateValidationException) {
            return new DateValidationExceptionHandler();
        }

        if (exception instanceof EmptyListException) {
            return new EmptyListExceptionHandler();
        }



        // Default ExceptionHandler
        return new DefaultExceptionHandler();

    }
}
