package com.rentalhive.stockManagement.helpers;

import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import com.rentalhive.stockManagement.entities.User;
import com.rentalhive.stockManagement.exceptions.costums.ValidationException;
import java.util.Optional;

public class ServiceHelper {

    // Check If object Is Null
    Predicate<Object> isNull = object -> object == null;

    // Check If Optional Object Is Empty
    Predicate<Optional<?>> isEmpty = object -> object.isEmpty();

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

    /**
     * Throws an exception if the object is null.
     *
     * @param object             the object to check
     * @param messageOfException the message for the exception
     * @return void
     */
    protected void throwExceptionIfObjectIsNull(Object object, String messageOfException) {
        // throwException If object is Null
        if (isNull.test(object)) {
            throw new ValidationException(List.of(messageOfException));
        }

    }

    /**
     * Throws a ValidationException if the given Optional object is empty.
     *
     * @param object             the Optional object to check
     * @param messageOfException the message for the ValidationException
     * @return nothing
     */
    protected void throwExceptionIfOptionalObjectIsEmpty(Optional<?> object, String messageOfException) {
        // throwException If object is empty
        if (isEmpty.test(object)) {
            throw new ValidationException(List.of(messageOfException));
        }
    }
}
