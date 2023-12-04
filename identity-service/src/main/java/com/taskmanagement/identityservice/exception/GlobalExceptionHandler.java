package com.taskmanagement.identityservice.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<ErrorResponse> handle(UnauthorizedException exception, HttpServletRequest request) {
        var status = UnauthorizedException.STATUS;
        ErrorResponse errorResponse = new ErrorResponse(status.value(), status.getReasonPhrase(), exception.getMessage(), System.currentTimeMillis(), request.getRequestURI());
        return new ResponseEntity<>(
                errorResponse,
                status
        );
    }
}
