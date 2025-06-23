package com.user.userservice.service;

import com.user.userservice.entity.User;
import com.user.userservice.exception.UserExistsException;
import com.user.userservice.exception.UserNotFoundException;
import com.user.userservice.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User createUser(User user) throws UserExistsException {
        Optional<User> existing = userRepository.findById(user.getId());
        if (existing.isPresent()) {
            throw new UserExistsException("User already exists with id " + user.getId());
        }
        return userRepository.save(user);
    }

    @Override
    public User getUserById(int id) throws UserNotFoundException {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found with id " + id));
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User updateUser(User user) throws UserNotFoundException {
        if (!userRepository.existsById(user.getId())) {
            throw new UserNotFoundException("User not found with id " + user.getId());
        }
        return userRepository.save(user);  // same logic as LoanServiceImpl
    }

    @Override
    public void deleteUser(int id) throws UserNotFoundException {
        if (!userRepository.existsById(id)) {
            throw new UserNotFoundException("User not found with id: " + id);
        }
        userRepository.deleteById(id);
    }
}
