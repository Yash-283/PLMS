package com.user.userservice.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;
import com.user.userservice.model.User;

@Repository
public class UserRepository {

    private List<User> users = new ArrayList<>();

    public List<User> getUsers() {
        return users;
    }

    public Optional<User> getUserById(int id) {
        return users.stream().filter(u -> u.getId() == id).findFirst();
    }

    public User addUser(User user) {
        users.add(user);
        return user;
    }

    public User updateUser(User user) {
        int index = users.indexOf(getUserById(user.getId()).get());
        users.set(index, user);
        return user;
    }

    public boolean deleteUser(int id) {
        return users.removeIf(u -> u.getId() == id);
    }
}
