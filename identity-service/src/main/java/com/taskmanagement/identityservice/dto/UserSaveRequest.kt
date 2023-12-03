package com.taskmanagement.identityservice.dto

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size

data class UserSaveRequest(

    @NotBlank
    @Size(min = 3, max = 55)
    val username: String,

    @NotBlank
    @Size(min = 3, max = 125)
    val firstName: String,

    @NotBlank
    @Size(min = 3, max = 125)
    val lastName: String,

    @Email
    val email: String,

    @NotNull
    @Size(min = 5)
    val password: String
)
