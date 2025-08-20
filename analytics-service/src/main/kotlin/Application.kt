package com.leobarreto

import com.leobarreto.Shared.Modules.configureAwsModules
import io.ktor.server.application.*

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
    configureSerialization()
    configureRouting()
    configureAwsModules()
}
