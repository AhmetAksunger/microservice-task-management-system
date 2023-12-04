package com.taskmanagement.identityservice.dto

import com.taskmanagement.identityservice.model.UserClaims
import io.jsonwebtoken.Claims

data class UserValidateTokenResponse(
    val id: String,
    val email: String
) {
    constructor(claims: Claims) : this(
        claims[UserClaims.USER_ID.value].toString(),
        claims[UserClaims.EMAIL.value].toString()
    )
}
