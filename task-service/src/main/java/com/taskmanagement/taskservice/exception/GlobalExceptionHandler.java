package com.taskmanagement.taskservice.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(TaskServiceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handle(TaskServiceNotFoundException exception, HttpServletRequest request) {
        var status = TaskServiceNotFoundException.STATUS;
        ErrorResponse errorResponse = new ErrorResponse(status.getReasonPhrase(), exception.getMessage(), status.value(), System.currentTimeMillis(), request.getRequestURI());
        return new ResponseEntity<>(
                errorResponse,
                status
        );
    }

    @ExceptionHandler(TaskServiceAlreadyException.class)
    public ResponseEntity<ErrorResponse> handle(TaskServiceAlreadyException exception, HttpServletRequest request) {
        var status = TaskServiceAlreadyException.STATUS;
        ErrorResponse errorResponse = new ErrorResponse(status.getReasonPhrase(), exception.getMessage(), status.value(), System.currentTimeMillis(), request.getRequestURI());
        return new ResponseEntity<>(
                errorResponse,
                status
        );
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponse> handle(UserNotFoundException exception) {
        return new ResponseEntity<>(exception.getResponse(), HttpStatus.NOT_FOUND);
    }
}
