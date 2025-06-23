package com.user.userservice.service;

import java.util.List;

import com.user.userservice.exception.UserExistsException;
import com.user.userservice.exception.UserNotFoundException;
import com.user.userservice.entity.User;




public interface UserService {
    User createUser(User user) throws UserExistsException;
    User getUserById(int id) throws UserNotFoundException;
    List<User> getAllUsers();
    User updateUser(User user) throws UserNotFoundException;
    void deleteUser(int id) throws UserNotFoundException;
}