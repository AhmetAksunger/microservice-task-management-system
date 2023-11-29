package com.taskmanagement.taskservice.service;

import com.taskmanagement.taskservice.dto.AssignUsersToTaskRequest;
import com.taskmanagement.taskservice.dto.CreateTaskRequest;
import com.taskmanagement.taskservice.dto.TaskDto;

import java.util.List;

public interface TaskService {

    void createTask(CreateTaskRequest request);

    TaskDto getTaskById(String taskId);

    List<TaskDto> getAllTasks();

    List<TaskDto> getAllUnassignedTasks();

    TaskDto assignUsersToTask(AssignUsersToTaskRequest request);
}
