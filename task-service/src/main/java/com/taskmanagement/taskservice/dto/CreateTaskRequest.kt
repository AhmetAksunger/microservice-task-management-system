package com.taskmanagement.taskservice.dto

import com.taskmanagement.taskservice.model.enums.Priority
import jakarta.validation.constraints.NotBlank

data class CreateTaskRequest(

    @NotBlank
    val title: String,

    @NotBlank
    val description: String,

    val priority: Priority,
)
