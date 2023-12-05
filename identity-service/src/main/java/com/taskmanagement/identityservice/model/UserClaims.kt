package com.taskmanagement.identityservice.model

enum class UserClaims(val value: String) {
    USER_ID("id"),
    EMAIL("email"),
    ROLE("role")
}