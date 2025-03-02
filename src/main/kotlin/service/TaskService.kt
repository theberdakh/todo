package com.theberdakh.service

import com.theberdakh.model.Task

object TaskService {
    private val tasks = mutableListOf<Task>()

    fun getAllTasks() = tasks.toList()

    fun addTask(task: Task): Task {
        tasks.add(task)
        return task
    }

    fun updateTask(id: Int, updatedTask: Task): Task? {
        val index = tasks.indexOfFirst { it.id == id }
        return if (index != -1) {
            val newTask = updatedTask.copy(id = id) // Keep the same ID
            tasks[index] = newTask
            newTask
        } else {
            null // Task not found
        }
    }

    fun deleteTask(id: Int): Boolean {
        val index = tasks.indexOfFirst { it.id == id }
        return if (index != -1) {
            tasks.removeAt(index)
            true
        } else {
            false
        }
    }
}