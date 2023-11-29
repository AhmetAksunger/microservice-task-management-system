package com.taskmanagement.taskservice.dto

data class UserDto(
    val id: String,
    val username: String,
    val firstName: String,
    val lastName: String,
    val email: String
) {
}
