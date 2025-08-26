package com.leobarreto.shortener_service.Shared.Config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider
import software.amazon.awssdk.regions.Region
import software.amazon.awssdk.services.sqs.SqsClient
import java.net.URI

@Configuration
class AwsConfig() {
    @Value("\${cloud.aws.credentials.access-key}")
    private lateinit var accessKey: String;
    @Value("\${cloud.aws.credentials.secret-key}")
    private lateinit var secretKey: String;
    @Value("\${cloud.aws.region.static}")
    private lateinit var region: String;
    @Value("\${cloud.aws.sqs.endpoint}")
    private lateinit var customEndpoint: String;

    @Bean
    fun SqsClient(): SqsClient {
        return SqsClient.builder()
            .region(Region.of(region))
            .credentialsProvider(awsCredentials())
            .endpointOverride(URI.create(customEndpoint))
            .build();
    }

    private fun awsCredentials(): StaticCredentialsProvider {
        return StaticCredentialsProvider.create(AwsBasicCredentials.create(accessKey, secretKey));
    }
}