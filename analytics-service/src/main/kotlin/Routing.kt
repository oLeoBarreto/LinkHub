package com.leobarreto

import com.leobarreto.Module.Health.Infrastructure.Http.configureHealthRouting
import com.leobarreto.Shared.Configs.AwsConfig
import com.leobarreto.Shared.Infrastructure.Queue.SqsQueueConsumer
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.response.*
import io.ktor.server.routing.get
import io.ktor.server.routing.route
import io.ktor.server.routing.routing

fun Application.configureRouting() {
    install(StatusPages) {
        exception<Throwable> { call, cause ->
            call.respondText(text = "500: $cause" , status = HttpStatusCode.InternalServerError)
        }
    }
    configureHealthRouting();
    routing {
        route("/queue") {
            get {
                val sqsClient = AwsConfig("mock_access_key", "mock_secret_key", "US-EAST-1").SqsClient();
                SqsQueueConsumer(sqsClient).receiveMessage();
                call.respond(HttpStatusCode.NoContent);
            }
        }
    }
}
