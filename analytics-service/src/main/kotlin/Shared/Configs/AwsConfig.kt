package com.leobarreto.Shared.Configs

import software.amazon.awssdk.auth.credentials.AwsBasicCredentials
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider
import software.amazon.awssdk.regions.Region
import software.amazon.awssdk.services.sqs.SqsClient
import java.net.URI

data class AwsConfig(
    val accessKeyId: String,
    val secretAccessKey: String,
    val region: String
) {
    fun SqsClient(): SqsClient {
        return SqsClient.builder()
            .region(Region.US_EAST_1)
            .credentialsProvider(awsCredentials())
            .endpointOverride(URI.create("http://localhost:4566"))
            .build();
    }

    private fun awsCredentials(): StaticCredentialsProvider {
        return StaticCredentialsProvider.create(AwsBasicCredentials.create(accessKeyId, secretAccessKey));
    }
}