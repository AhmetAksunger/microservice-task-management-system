package com.taskmanagement.taskservice.exception;

import org.springframework.http.HttpStatus;

public abstract class TaskServiceNotFoundException extends RuntimeException {

    protected static final HttpStatus STATUS = HttpStatus.NOT_FOUND;

    protected TaskServiceNotFoundException(String message) {
        super(message);
    }

}
