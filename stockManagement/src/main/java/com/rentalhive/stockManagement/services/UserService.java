package com.rentalhive.stockManagement.services;

import com.rentalhive.stockManagement.entities.Category;
import com.rentalhive.stockManagement.entities.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    public List<User> getAllUsers();

    public User find(User user);

    public User addUser(User user);

    public User updateUser(User user);

    public void deleteUser(User user);

    public Optional<User> findById(long id);
}
