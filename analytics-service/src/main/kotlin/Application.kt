package com.leobarreto

import com.leobarreto.Shared.Modules.configureCoroutineModule
import com.leobarreto.Shared.Modules.configureCustomModules
import io.ktor.server.application.*

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
    configureSerialization()
    configureRouting()
    configureCustomModules()
    configureCoroutineModule()
}
