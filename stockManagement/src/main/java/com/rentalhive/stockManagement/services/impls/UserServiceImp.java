package com.rentalhive.stockManagement.services.impls;

import com.rentalhive.stockManagement.entities.Category;
import com.rentalhive.stockManagement.entities.User;
import com.rentalhive.stockManagement.repositories.UserRepository;
import com.rentalhive.stockManagement.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImp implements UserService {

    private UserRepository userRepository;
    public List<User> getAllUsers() {
        return null;
    }
    public Optional<User> findById(Long id) {
        return Optional.empty();
    }

    public User addUser(User user) {
        return null;
    }

    public User updateUser(User user) {
        return null;
    }

    public void deleteUser(User user) {

    }

    public boolean find(User user) {
       return false;
    }
}
