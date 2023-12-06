package com.taskmanagement.taskservice.model

import com.taskmanagement.taskservice.model.enums.Priority
import jakarta.persistence.*

@Entity
@Table(name = "TASKS")
data class Task @JvmOverloads constructor(

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: String? = "",
    val title: String,
    val description: String,
    val completed: Boolean = false,

    @Enumerated(EnumType.STRING)
    val priority: Priority,

    @ElementCollection
    val assignedUserIds: List<String>? = ArrayList()
) {
}
