package com.taskmanagement.identityservice.exception;

import org.springframework.http.HttpStatus;

public class UnauthorizedException extends RuntimeException {

    protected static final HttpStatus STATUS = HttpStatus.UNAUTHORIZED;

    public UnauthorizedException() {
        super("Unauthorized!");
    }

    public UnauthorizedException(String message) {
        super(message);
    }
}
