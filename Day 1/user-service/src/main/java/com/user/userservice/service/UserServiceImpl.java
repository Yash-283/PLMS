package com.user.userservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.user.userservice.exception.UserExistsException;
import com.user.userservice.exception.UserNotFoundException;
import com.user.userservice.model.User;
import com.user.userservice.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> getUsers() {
        return userRepository.getUsers();
    }

    @Override
    public User getUserById(int id) throws UserNotFoundException {
        Optional<User> user = userRepository.getUserById(id);
        if (!user.isPresent()) {
            throw new UserNotFoundException("User not found with ID: " + id);
        }
        return user.get();
    }

    @Override
    public User addUser(User user) throws UserExistsException {
        Optional<User> existing = userRepository.getUserById(user.getId());
        if (existing.isPresent()) {
            throw new UserExistsException("User already exists with ID: " + user.getId());
        }
        return userRepository.addUser(user);
    }

    @Override
    public User updateUser(User user) throws UserNotFoundException {
        Optional<User> existing = userRepository.getUserById(user.getId());
        if (!existing.isPresent()) {
            throw new UserNotFoundException("User not found with ID: " + user.getId());
        }
        return userRepository.updateUser(user);
    }

    @Override
    public boolean deleteUser(int id) throws UserNotFoundException {
        Optional<User> existing = userRepository.getUserById(id);
        if (!existing.isPresent()) {
            throw new UserNotFoundException("User not found with ID: " + id);
        }
        return userRepository.deleteUser(id);
    }
}