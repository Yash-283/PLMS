package com.user.userservice.exception;

public class UserExistsException extends Exception {
    public UserExistsException(String message) {
        super(message);
    }
}
