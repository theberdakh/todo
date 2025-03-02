package com.theberdakh.route

import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.default(){
    get("/") {
        call.respondText("Hello World!")
    }
    get("/json/kotlinx-serialization") {
        call.respond(mapOf("hello" to "world"))
    }
}