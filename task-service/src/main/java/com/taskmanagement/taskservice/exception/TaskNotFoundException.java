package com.taskmanagement.taskservice.exception;

public class TaskNotFoundException extends TaskServiceNotFoundException {
    public TaskNotFoundException(String message) {
        super(message);
    }
}
