package com.leobarreto.Shared.Modules

import com.leobarreto.Shared.Configs.*
import com.leobarreto.Shared.Infrastructure.Queue.SqsQueueConsumer
import io.ktor.server.application.Application
import io.ktor.server.application.install
import org.koin.ktor.plugin.Koin
import org.koin.dsl.module
import org.koin.logger.slf4jLogger

fun Application.configureAwsModules() {
    install(Koin) {
        slf4jLogger()
        modules(
            module {
                single { awsConfig() }
                single { get<AwsConfig>().SqsClient() }
                single { SqsQueueConsumer(get()) }
            }
        )
        modules(coroutineModule)
    }
}