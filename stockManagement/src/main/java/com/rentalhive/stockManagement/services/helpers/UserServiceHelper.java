package com.rentalhive.stockManagement.services.helpers;

import com.rentalhive.stockManagement.helpers.ServiceHelper;
import com.rentalhive.stockManagement.entities.User;
import java.util.Optional;

public class UserServiceHelper extends ServiceHelper {

    public void throwExceptionIfIdOfUserIsNull(Long id) {
        throwExceptionIfObjectIsNull(id, "The ID of User can not be null");
    }

    public void thowExceptionIfUserIsEmpty(Optional<User> user) {
        throwExceptionIfOptionalObjectIsEmpty(user, "The User do not exist");
    }

}
