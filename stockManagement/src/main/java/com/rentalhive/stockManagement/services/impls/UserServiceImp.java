package com.rentalhive.stockManagement.services.impls;

import com.rentalhive.stockManagement.entities.User;
import com.rentalhive.stockManagement.repositories.EquipmentRepository;
import com.rentalhive.stockManagement.repositories.UserRepository;
import com.rentalhive.stockManagement.services.UserService;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import com.rentalhive.stockManagement.services.helpers.UserServiceHelper;

import java.util.List;
import java.util.Optional;

@Service @RequiredArgsConstructor
public class UserServiceImp extends UserServiceHelper implements UserService {

    private final UserRepository userRepository;

    public UserServiceImp(UserRepository userRepository){

        this.userRepository = userRepositor;
    }

    public List<User> getAllUsers() {
        return null;
    }

    public User findById(Long id) {

        throwExceptionIfIdOfUserIsNull(id);

        Optional<User> user = userRepository.findById(id);

        thowExceptionIfUserIsEmpty(user);

        return user.get();
    }

    public User addUser(User user) {
        return null;
    }

    public User updateUser(User user) {
        return null;
    }

    public void deleteUser(User user) {

    }

    public boolean isExists(User user) {
        return userRepository.existsById(user.getId());
    }
}
