package com.taskmanagement.userservice.exception;

import org.springframework.http.HttpStatus;

public abstract class UserServiceAlreadyException extends RuntimeException {

    protected static final HttpStatus STATUS = HttpStatus.CONFLICT;

    protected UserServiceAlreadyException(String msg) {
        super(msg);
    }
}
