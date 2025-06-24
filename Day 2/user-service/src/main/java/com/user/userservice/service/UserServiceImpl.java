package com.user.userservice.service;

import com.user.userservice.entity.User;
import com.user.userservice.exception.UserExistsException;
import com.user.userservice.exception.UserNotFoundException;
import com.user.userservice.repository.UserRepository;
import com.user.userservice.dto.Loan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    @Autowired
    private final RestTemplate restTemplate;

    public UserServiceImpl(UserRepository userRepository, RestTemplate restTemplate) {
        this.userRepository = userRepository;
        this.restTemplate = restTemplate;
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
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(int id) throws UserNotFoundException {
        if (!userRepository.existsById(id)) {
            throw new UserNotFoundException("User not found with id: " + id);
        }
        userRepository.deleteById(id);
    }


    public List<Loan> fetchAllLoansFromLoanService() {
        Loan[] loans = restTemplate.getForObject("http://loanservice/api/v1/loans", Loan[].class);
        return Arrays.asList(loans);
    }
}
