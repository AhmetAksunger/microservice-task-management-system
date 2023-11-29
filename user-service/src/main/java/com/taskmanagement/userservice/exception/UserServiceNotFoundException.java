package com.taskmanagement.userservice.exception;

import org.springframework.http.HttpStatus;

public abstract class UserServiceNotFoundException extends RuntimeException {

    protected static final HttpStatus STATUS = HttpStatus.NOT_FOUND;

    protected UserServiceNotFoundException(String message) {
        super(message);
    }

}
