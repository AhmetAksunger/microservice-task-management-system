package com.taskmanagement.userservice.exception;

public class EmailAlreadyExistsException extends UserServiceAlreadyException {
    public EmailAlreadyExistsException(String msg) {
        super(msg);
    }
}
