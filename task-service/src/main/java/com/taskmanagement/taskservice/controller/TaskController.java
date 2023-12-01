package com.taskmanagement.taskservice.controller;

import com.taskmanagement.taskservice.dto.AssignUsersToTaskRequest;
import com.taskmanagement.taskservice.dto.CreateTaskRequest;
import com.taskmanagement.taskservice.dto.TaskDto;
import com.taskmanagement.taskservice.service.TaskService;
import org.hibernate.validator.constraints.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
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

    private final Logger logger = LoggerFactory.getLogger(TaskController.class);
    private final TaskService taskService;
    private final Environment environment;

    public TaskController(TaskService taskService, Environment environment) {
        this.taskService = taskService;
        this.environment = environment;
    }

    @PostMapping("/tasks")
    public ResponseEntity<Void> createTask(@RequestBody CreateTaskRequest request) {

        logger.info("Server port is : {}", environment.getProperty("local.server.port"));

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
}
