package com.taskmanagement.taskservice.service.impl;

import com.taskmanagement.taskservice.client.UserServiceClient;
import com.taskmanagement.taskservice.dto.AssignUsersToTaskRequest;
import com.taskmanagement.taskservice.dto.CreateTaskRequest;
import com.taskmanagement.taskservice.dto.TaskDto;
import com.taskmanagement.taskservice.dto.UserDto;
import com.taskmanagement.taskservice.exception.TaskNotFoundException;
import com.taskmanagement.taskservice.exception.UsersAlreadyAssignedToTask;
import com.taskmanagement.taskservice.model.Task;
import com.taskmanagement.taskservice.repository.TaskRepository;
import com.taskmanagement.taskservice.service.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Stream;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final UserServiceClient userServiceClient;

    public TaskServiceImpl(TaskRepository taskRepository, UserServiceClient userServiceClient) {
        this.taskRepository = taskRepository;
        this.userServiceClient = userServiceClient;
    }

    @Override
    public void createTask(CreateTaskRequest request) {
        Task task = new Task(request.getTitle(), request.getDescription(), request.getPriority());
        taskRepository.save(task);
    }

    @Override
    public TaskDto getTaskById(String taskId) {
        Task task = taskRepository.findById(taskId).orElseThrow(() -> new TaskNotFoundException(taskId));
        return new TaskDto(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.isCompleted(),
                task.getAssigneeIds()
                        .stream()
                        .map(userServiceClient::getUserById)
                        .map(ResponseEntity::getBody)
                        .toList()
        );
    }

    @Override
    public List<TaskDto> getAllTasks() {
        return taskRepository.findAll().stream()
                .map(task -> new TaskDto(
                        task.getId(),
                        task.getTitle(),
                        task.getDescription(),
                        task.isCompleted(),
                        task.getAssigneeIds()
                                .stream()
                                .map(userServiceClient::getUserById)
                                .map(ResponseEntity::getBody).toList()
                )).toList();
    }

    @Override
    public List<TaskDto> getAllUnassignedTasks() {
        return taskRepository.findAllByAssignedUserIdsEmpty()
                .stream()
                .map(task -> new TaskDto(
                        task.getId(),
                        task.getTitle(),
                        task.getDescription(),
                        task.isCompleted())
                )
                .toList();
    }

    @Override
    public TaskDto assignUsersToTask(AssignUsersToTaskRequest request) {
        Task task = taskRepository.findById(request.getTaskId())
                .orElseThrow(() -> new TaskNotFoundException(request.getTaskId()));

        List<String> commonUserIds = request.getUserIds()
                .stream()
                .filter(task.getAssigneeIds()::contains)
                .toList();

        if (!commonUserIds.isEmpty()) {
            throw new UsersAlreadyAssignedToTask("Specified users are already assigned to the task!", request.getTaskId(), commonUserIds);
        }

        List<UserDto> userDtosToBeAssigned = request.getUserIds()
                .stream()
                .map(userServiceClient::getUserById)
                .map(ResponseEntity::getBody)
                .toList();

        List<UserDto> previouslyAssignedUserDtos = task.getAssigneeIds()
                .stream()
                .map(userServiceClient::getUserById)
                .map(ResponseEntity::getBody)
                .toList();

        task.getAssigneeIds().addAll(request.getUserIds());
        taskRepository.save(task);

        return new TaskDto(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.isCompleted(),
                Stream.concat(userDtosToBeAssigned.stream(), previouslyAssignedUserDtos.stream()).toList()
        );
    }

    @Override
    public void completeTask(String taskId) {

        Task task = taskRepository.findById(taskId).orElseThrow
                (() -> new TaskNotFoundException(taskId));
        task.complete();
        taskRepository.save(task);
    }
}
