package com.taskmanagement.identityservice.dto

data class UserTokenRequest(
    val email: String,
    val password: String
)
