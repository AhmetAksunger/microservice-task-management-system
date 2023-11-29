package com.taskmanagement.taskservice.dto

import jakarta.validation.constraints.NotBlank
import org.hibernate.validator.constraints.UUID

data class AssignUsersToTaskRequest(

    @UUID
    val taskId: String,

    @NotBlank
    val userIds: List<String>
)
