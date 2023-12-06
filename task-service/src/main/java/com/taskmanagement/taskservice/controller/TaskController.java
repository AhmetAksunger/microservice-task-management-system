package com.taskmanagement.taskservice.controller;

import com.taskmanagement.taskservice.dto.AssignUsersToTaskRequest;
import com.taskmanagement.taskservice.dto.CreateTaskRequest;
import com.taskmanagement.taskservice.dto.TaskDto;
import com.taskmanagement.taskservice.service.TaskService;
import org.hibernate.validator.constraints.UUID;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@Validated
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping("/tasks")
    public ResponseEntity<Void> createTask(@RequestBody CreateTaskRequest request) {

        taskService.createTask(request);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/tasks/{taskId}")
    public ResponseEntity<TaskDto> getTaskById(@PathVariable @UUID String taskId) {
        return ResponseEntity.ok(taskService.getTaskById(taskId));
    }

    @GetMapping("/tasks")
    public ResponseEntity<List<TaskDto>> getAllTasks() {
        return ResponseEntity.ok(taskService.getAllTasks());
    }

    @GetMapping("/tasks/unassigned")
    public ResponseEntity<List<TaskDto>> getAllUnassignedTasks() {
        return ResponseEntity.ok(taskService.getAllUnassignedTasks());
    }

    @PostMapping("/tasks/assign")
    public ResponseEntity<TaskDto> assignUsersToTask(@RequestBody AssignUsersToTaskRequest request) {
        return ResponseEntity.ok(taskService.assignUsersToTask(request));
    }

    @PostMapping("/tasks/complete/{taskId}")
    public ResponseEntity<Void> completeTask(@PathVariable @UUID String taskId) {
        taskService.completeTask(taskId);
        return ResponseEntity.ok().build();
    }
}
