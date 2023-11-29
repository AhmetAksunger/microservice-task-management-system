package com.taskmanagement.taskservice.dto

data class TaskDto @JvmOverloads constructor(
    val id: String? = "",
    val title: String,
    val description: String,
    val completed: Boolean,

    val assignedUsers: List<UserDto>? = ArrayList()
) {
}
