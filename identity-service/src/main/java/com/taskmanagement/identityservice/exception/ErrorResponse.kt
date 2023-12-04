package com.taskmanagement.identityservice.exception

data class ErrorResponse @JvmOverloads constructor(
    val status: Int,
    val error: String,
    val message: String? = "No specified error message",
    val timestamp: Long,
    val path: String
) {
}