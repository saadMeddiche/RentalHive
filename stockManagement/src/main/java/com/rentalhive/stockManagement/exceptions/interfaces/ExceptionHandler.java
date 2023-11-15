package com.rentalhive.stockManagement.exceptions.interfaces;

import java.util.List;

public interface ExceptionHandler {

    public List<String> handleException(Exception exception);

    public String getMessage();
}
