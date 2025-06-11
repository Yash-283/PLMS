package com.user.userservice.repository;

import java.util.List;

import com.user.userservice.model.User;

public interface UserRepository {
    User save(User user);
    User update(User user);
    User findById(int id);
    List<User> findAll();
    void deleteById(int id);
}