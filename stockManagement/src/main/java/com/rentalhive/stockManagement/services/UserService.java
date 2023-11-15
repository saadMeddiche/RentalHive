package com.rentalhive.stockManagement.services;

import com.rentalhive.stockManagement.entities.User;

import java.util.List;

public interface UserService {

    public List<User> getAllUsers();

    public User addUser(User user);

    public User updateUser(User user);

    public void deleteUser(User user);
}