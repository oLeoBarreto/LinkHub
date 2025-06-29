package com.leobarreto.Module.Health.Infrastructure.Http

import com.leobarreto.Module.Health.Domain.HeathStatus
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.respond
import io.ktor.server.routing.*

fun Application.configureHealthRouting() {
    routing {
        route("/health") {
            get {
                val response = HeathStatus("Up");
                call.respond(HttpStatusCode.OK, response);
            }
        }
    }
}