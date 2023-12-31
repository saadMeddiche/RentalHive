package com.rentalhive.stockManagement.services.impls;

import com.rentalhive.stockManagement.entities.User;
import com.rentalhive.stockManagement.repositories.UserRepository;
import com.rentalhive.stockManagement.services.UserService;

import org.springframework.stereotype.Service;
import com.rentalhive.stockManagement.services.helpers.UserServiceHelper;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImp extends UserServiceHelper implements UserService {

    private final UserRepository userRepository;

    public UserServiceImp(UserRepository userRepository) {
        this.userRepository = userRepository;
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

    public Optional<User> findUserById(Long id) {
        return userRepository.findById(id);
    }
}
