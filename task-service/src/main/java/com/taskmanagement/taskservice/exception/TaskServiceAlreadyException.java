package com.taskmanagement.taskservice.exception;

import org.springframework.http.HttpStatus;

public abstract class TaskServiceAlreadyException extends RuntimeException {
    protected static final HttpStatus STATUS = HttpStatus.CONFLICT;

    protected TaskServiceAlreadyException(String message) {
        super(message);
    }
}
