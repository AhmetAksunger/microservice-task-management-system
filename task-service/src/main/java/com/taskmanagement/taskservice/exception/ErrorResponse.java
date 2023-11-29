package com.taskmanagement.taskservice.exception;

public record ErrorResponse(String error,
                            String message,
                            int status,
                            long timestamp,
                            String path) {
}
