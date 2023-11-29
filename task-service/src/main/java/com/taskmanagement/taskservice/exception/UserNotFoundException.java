package com.taskmanagement.taskservice.exception;

public class UserNotFoundException extends RuntimeException {

    private final ErrorResponse response;

    public UserNotFoundException(ErrorResponse response) {
        this.response = response;
    }

    public ErrorResponse getResponse() {
        return response;
    }
}
