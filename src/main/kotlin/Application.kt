package com.theberdakh

import com.theberdakh.model.FakeTaskRepository
import com.theberdakh.route.default
import com.theberdakh.route.tasks
import io.ktor.server.application.*
import io.ktor.server.http.content.*
import io.ktor.server.routing.*

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
    val repository = FakeTaskRepository()
    configureSerialization()
    routing {
        singlePageApplication {
            useResources = true
        }
        default()
        tasks(repository)
    }
}
