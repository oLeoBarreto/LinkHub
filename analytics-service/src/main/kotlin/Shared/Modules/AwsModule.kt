package com.leobarreto.Shared.Modules

import com.leobarreto.Shared.Configs.*
import com.leobarreto.Shared.Infrastructure.Queue.SqsQueueConsumer
import io.ktor.server.application.Application
import io.ktor.server.application.install
import org.koin.ktor.plugin.Koin
import org.koin.dsl.module

val awsModule = module {
    single { AwsConfig(
        System.getenv("access_key"),
        System.getenv("secret_key"),
        System.getenv("region")
    ) }
    single { get<AwsConfig>().SqsClient() }
    single { SqsQueueConsumer(get()) }
}

fun Application.configureAwsModules() {
    install(Koin) {
        modules(awsModule)
    }
}