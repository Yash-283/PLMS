package com.user.userservice.service;

import java.util.List;

import com.user.userservice.model.User;

public interface UserService {
    User addUser(User user);
    User updateUser(User user);
    User getUserById(int id);
    List<User> getAllUsers();
    void deleteUserById(int id);
}
