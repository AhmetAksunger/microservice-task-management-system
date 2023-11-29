package com.taskmanagement.taskservice.dto

import jakarta.validation.constraints.NotBlank

data class CreateTaskRequest(

    @NotBlank
    val title: String,

    @NotBlank
    val description: String,
)
