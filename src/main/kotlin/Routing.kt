package com.theberdakh

import com.theberdakh.model.Task
import com.theberdakh.service.TaskService
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    routing {
        get("/") {
            call.respondText("Hello World!")
        }
    }
    routing {
        get("/tasks") {
            call.respond(HttpStatusCode.OK, TaskService.getAllTasks())
        }
        post("/tasks/create") {
            val task = call.receive<Task>()
            call.respond(HttpStatusCode.OK, TaskService.addTask(task))
        }
        put("/tasks/{id}") {
            val id = call.parameters["id"]?.toIntOrNull()
            if (id == null || id <= 0) {
                return@put call.respond(HttpStatusCode.BadRequest, "Invalid ID format. ID must be a positive integer.")
            }
            val updatedTask = call.receive<Task>()
            val result = TaskService.updateTask(id, updatedTask)
            if (result != null) {
                call.respond(HttpStatusCode.OK, result)
            } else {
                call.respond(HttpStatusCode.NotFound, "Task with ID $id not found.")
            }
        }
        delete("/tasks/{id}") {
            val id = call.parameters["id"]?.toIntOrNull()
            if (id == null || id <= 0) {
                return@delete call.respond(HttpStatusCode.BadRequest, "Invalid ID format. ID must be a positive integer.")
            }
            else {
                val isDeleted = TaskService.deleteTask(id)
                if (isDeleted){
                    call.respond(HttpStatusCode.OK)
                } else {
                    call.respond(HttpStatusCode.NotFound, "Task with ID $id not found.")
                }
            }
        }
    }
}
