package com.taskmanagement.taskservice.repository;

import com.taskmanagement.taskservice.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, String> {

    List<Task> findAllByAssignedUserIdsEmpty();

}
