package com.taskmanagement.userservice.dto

import com.taskmanagement.userservice.model.User

data class UserDto @JvmOverloads constructor(
    val id: String? = "",
    val username: String,
    val firstName: String,
    val lastName: String,
    val email: String
) {
    companion object {
        @JvmStatic
        fun convert(from: User): UserDto {
            return UserDto(
                from.id,
                from.username,
                from.firstName,
                from.lastName,
                from.email
            )
        }
    }
}
