package com.theberdakh.model

import kotlinx.serialization.Serializable

enum class Priority {
    Low, Medium, High
}

@Serializable
data class Task(
    val name: String,
    val description: String,
    val priority: Priority,
    val completed: Boolean = false
)
