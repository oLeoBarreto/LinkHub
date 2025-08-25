package com.leobarreto.Shared.Configs

import io.ktor.server.application.Application
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider
import software.amazon.awssdk.regions.Region
import software.amazon.awssdk.services.sqs.SqsClient
import java.net.URI

class AwsConfig(
    val accessKeyId: String,
    val secretAccessKey: String,
    val region: String,
    val customEndpoint: String
) {
    fun SqsClient(): SqsClient {
        return SqsClient.builder()
            .region(Region.of(region))
            .credentialsProvider(awsCredentials())
            .endpointOverride(URI.create(customEndpoint))
            .build();
    }

    private fun awsCredentials(): StaticCredentialsProvider {
        return StaticCredentialsProvider.create(AwsBasicCredentials.create(accessKeyId, secretAccessKey));
    }
}

fun Application.awsConfig(): AwsConfig {
    val accessKey = environment.config.property("aws.access_key").getString()
    val secretKey = environment.config.property("aws.secret_key").getString()
    val region = environment.config.property("aws.region").getString()
    val customEndpoint = environment.config.propertyOrNull("aws.custom_endpoint")?.getString() ?: ""

    return AwsConfig(accessKey, secretKey, region, customEndpoint)
}