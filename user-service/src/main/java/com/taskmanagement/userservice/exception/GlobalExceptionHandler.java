package com.taskmanagement.userservice.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserServiceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handle(UserServiceNotFoundException exception, HttpServletRequest request) {
        var status = UserServiceNotFoundException.STATUS;
        ErrorResponse errorResponse = new ErrorResponse(status.value(), status.getReasonPhrase(), exception.getMessage(), System.currentTimeMillis(), request.getRequestURI());
        return new ResponseEntity<>(
                errorResponse,
                status
        );
    }

    @ExceptionHandler(UserServiceAlreadyException.class)
    public ResponseEntity<ErrorResponse> handle(UserServiceAlreadyException exception, HttpServletRequest request) {
        var status = UserServiceAlreadyException.STATUS;
        ErrorResponse errorResponse = new ErrorResponse(status.value(), status.getReasonPhrase(), exception.getMessage(), System.currentTimeMillis(), request.getRequestURI());
        return new ResponseEntity<>(
                errorResponse,
                status
        );
    }
}
