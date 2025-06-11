package com.user.userservice.service;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.user.userservice.exception.UserExistsException;
import com.user.userservice.exception.UserNotFoundException;
import com.user.userservice.model.User;

@Service
public class UserServiceImpl implements UserService {

    private Map<Integer, User> userDB = new HashMap<>();

    @Override
    public User addUser(User user) {
        if (userDB.containsKey(user.getId())) {
            throw new UserExistsException("User with ID " + user.getId() + " already exists.");
        }
        userDB.put(user.getId(), user);
        return user;
    }

    @Override
    public User updateUser(User user) {
        if (!userDB.containsKey(user.getId())) {
            throw new UserNotFoundException("User not found with ID: " + user.getId());
        }
        userDB.put(user.getId(), user);
        return user;
    }

    @Override
    public User getUserById(int id) {
        User user = userDB.get(id);
        if (user == null) {
            throw new UserNotFoundException("User not found with ID: " + id);
        }
        return user;
    }

    @Override
    public List<User> getAllUsers() {
        return new ArrayList<>(userDB.values());
    }

    @Override
    public void deleteUserById(int id) {
        if (!userDB.containsKey(id)) {
            throw new UserNotFoundException("User not found with ID: " + id);
        }
        userDB.remove(id);
    }
}