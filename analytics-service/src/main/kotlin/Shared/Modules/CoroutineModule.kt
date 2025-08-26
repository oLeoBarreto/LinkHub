package com.leobarreto.Shared.Modules

import com.leobarreto.Shared.Infrastructure.Coroutines.QueueStartingRoutine
import io.ktor.server.application.Application
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import org.koin.dsl.module
import org.koin.ktor.ext.inject
import kotlin.getValue

val coroutineModule = module {
    single { QueueStartingRoutine(get(), get()) }
}

fun Application.configureCoroutineModule() {
    val routine by inject<QueueStartingRoutine>();

    val scope = CoroutineScope(Dispatchers.IO + coroutineContext);
    routine.startConsuming(scope);
}