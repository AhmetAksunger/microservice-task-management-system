package com.taskmanagement.userservice.dto

data class UserValidateTokenResponse(
    val id: String,
    val email: String,
    val role: String
)
