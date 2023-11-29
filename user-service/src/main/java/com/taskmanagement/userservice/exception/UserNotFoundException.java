package com.taskmanagement.userservice.exception;

public class UserNotFoundException extends UserServiceNotFoundException{
    public UserNotFoundException(String message) {
        super(message);
    }
}
