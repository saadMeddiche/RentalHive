package com.rentalhive.stockManagement.services.helpers;

import com.rentalhive.stockManagement.helpers.ServiceHelper;
import com.rentalhive.stockManagement.entities.Category;

import java.util.Optional;

public class CategoryServiceHelper extends ServiceHelper {

    public void throwExceptionIfIdOfCategoryIsNull(Long id) {
        throwExceptionIfObjectIsNull(id, "The ID of Category can not be null");
    }

    public void thowExceptionIfCategoryIsEmpty(Optional<Category> category) {
        throwExceptionIfOptionalObjectIsEmpty(category, "The Category do not exist");
    }

}