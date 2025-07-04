package com.user.userservice.service;

import com.user.userservice.dto.NotificationMessage;
import com.user.userservice.entity.User;
import com.user.userservice.exception.UserExistsException;
import com.user.userservice.exception.UserNotFoundException;
import com.user.userservice.repository.UserRepository;
import com.user.userservice.dto.Loan;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import io.github.resilience4j.retry.annotation.Retry;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    private final RestTemplate restTemplate;

    @Autowired
    private RabbitTemplate rabbitTemplate;


    public UserServiceImpl(UserRepository userRepository, RestTemplate restTemplate) {
        this.userRepository = userRepository;
        this.restTemplate = restTemplate;
    }


    @Autowired
    public void setRabbitTemplate(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }


    @Override
    public User createUser(User user) throws UserExistsException {
        Optional<User> existing = userRepository.findById(user.getId());
        if (existing.isPresent()) {
            throw new UserExistsException("User already exists with id " + user.getId());
        }

        User savedUser = userRepository.save(user);

        // 🔔 Send notification for CREATE
        NotificationMessage message = new NotificationMessage(
                "user-service",
                "CREATE",
                "New user created: " + savedUser.getId() + " - " + savedUser.getName(), // Added user name for more info
                LocalDateTime.now()
        );
        rabbitTemplate.convertAndSend("notificationQueue", message);
        System.out.println("✉️ Sent user creation notification to RabbitMQ for user: " + savedUser.getId());


        return savedUser;
    }

    @Override
    public User getUserById(int id) throws UserNotFoundException {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found with id " + id));

        // 🔔 Send notification for READ (single user)
        NotificationMessage message = new NotificationMessage(
                "user-service",
                "READ",
                "User viewed by ID: " + user.getId() + " - " + user.getName(), // Added user name for more info
                LocalDateTime.now()
        );
        rabbitTemplate.convertAndSend("notificationQueue", message);
        System.out.println("✉️ Sent user read notification to RabbitMQ for user: " + user.getId());

        return user;
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = userRepository.findAll();

        // 🔔 Send notification for READ_ALL
        // IMPORTANT: Consider the volume of these notifications.
        // If this endpoint is called very frequently, it could flood your RabbitMQ and notification service.
        NotificationMessage message = new NotificationMessage(
                "user-service",
                "READ_ALL",
                "All users retrieved. Count: " + users.size(),
                LocalDateTime.now()
        );
        rabbitTemplate.convertAndSend("notificationQueue", message);
        System.out.println("✉️ Sent notification for all users retrieved. Count: " + users.size());

        return users;
    }

    @Override
    public User updateUser(User user) throws UserNotFoundException {
        if (!userRepository.existsById(user.getId())) {
            throw new UserNotFoundException("User not found with id " + user.getId());
        }

        User updatedUser = userRepository.save(user);

        // 🔔 Send notification for UPDATE
        NotificationMessage message = new NotificationMessage(
                "user-service",
                "UPDATE",
                "User updated: " + updatedUser.getId() + " - " + updatedUser.getName(), // Added user name for more info
                LocalDateTime.now()
        );
        rabbitTemplate.convertAndSend("notificationQueue", message);
        System.out.println("✉️ Sent user update notification to RabbitMQ for user: " + updatedUser.getId());

        return updatedUser;
    }

    @Override
    public void deleteUser(int id) throws UserNotFoundException {
        if (!userRepository.existsById(id)) {
            throw new UserNotFoundException("User not found with id: " + id);
        }
        userRepository.deleteById(id);

        // 🔔 Send notification for DELETE
        NotificationMessage message = new NotificationMessage(
                "user-service",
                "DELETE",
                "User deleted: " + id,
                LocalDateTime.now()
        );
        rabbitTemplate.convertAndSend("notificationQueue", message);
        System.out.println("✉️ Sent user deletion notification to RabbitMQ for user: " + id);
    }


    @CircuitBreaker(name = "loanService", fallbackMethod = "loanServiceFallback")
    @Retry(name = "loanService")
    @Override
    public ResponseEntity<?> fetchAllLoansFromLoanService() {
        Loan[] loans = restTemplate.getForObject("http://loanservice/api/v1/loans", Loan[].class);
        return ResponseEntity.ok(Arrays.asList(loans));
    }

    public ResponseEntity<?> loanServiceFallback(Exception e) {
        return ResponseEntity
                .status(HttpStatus.SERVICE_UNAVAILABLE)
                .body(Map.of("message", "Loan Service is currently unavailable. Please try again later."));
    }
}