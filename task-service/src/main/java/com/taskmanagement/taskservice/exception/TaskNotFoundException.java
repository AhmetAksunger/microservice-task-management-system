package com.taskmanagement.taskservice.exception;

public class TaskNotFoundException extends TaskServiceNotFoundException {
    public TaskNotFoundException(String id) {
        super("Task not found with id: " + id);
    }
}
