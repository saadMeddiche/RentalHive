package com.rentalhive.stockManagement.helpers;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import com.rentalhive.stockManagement.exceptions.costums.ValidationException;

public class ServiceHelper {

    /**
     * Validates an object using the default validator.
     *
     * @param object the object to be validated
     * @return void
     */
    protected <O> void validateObject(O object) {

        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

        Set<ConstraintViolation<O>> violations = validator.validate(object);

        if (!violations.isEmpty()) {
            List<String> errors = violations.stream()
                    .map(ConstraintViolation::getMessage)
                    .collect(Collectors.toList());
            throw new ValidationException(errors);
        }
    }
}
