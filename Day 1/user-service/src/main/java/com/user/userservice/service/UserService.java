package com.user.userservice.service;

import java.util.List;

import com.user.userservice.exception.UserExistsException;
import com.user.userservice.exception.UserNotFoundException;
import com.user.userservice.model.User;


public interface UserService {
    List<User> getUsers();
    User getUserById(int id) throws UserNotFoundException;
    User addUser(User user) throws UserExistsException;
    User updateUser(User user) throws UserNotFoundException;
    boolean deleteUser(int id) throws UserNotFoundException;
}
