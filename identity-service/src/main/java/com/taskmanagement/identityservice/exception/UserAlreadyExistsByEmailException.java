package com.taskmanagement.identityservice.exception;

import org.springframework.http.HttpStatus;

public class UserAlreadyExistsByEmailException extends RuntimeException {

    public static final HttpStatus STATUS = HttpStatus.CONFLICT;

    public UserAlreadyExistsByEmailException(String msg) {
        super(msg);
    }
}
