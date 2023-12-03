package com.taskmanagement.userservice.model

import jakarta.persistence.*

@Entity
@Table(name = "USERS")
data class User @JvmOverloads constructor(

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: String? = "",
    val username: String,
    val firstName: String,
    val lastName: String,
    val email: String,
)
