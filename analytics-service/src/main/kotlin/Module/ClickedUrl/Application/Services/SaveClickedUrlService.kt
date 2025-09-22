package com.leobarreto.Module.ClickedUrl.Application.Services

import com.leobarreto.Module.ClickedUrl.Application.Contracts.ISaveClickedUrlContract
import com.leobarreto.Module.ClickedUrl.Domain.ClickedUrl
import io.ktor.server.plugins.BadRequestException
import org.slf4j.LoggerFactory
import software.amazon.awssdk.services.dynamodb.DynamoDbClient
import software.amazon.awssdk.services.dynamodb.model.AttributeValue
import software.amazon.awssdk.services.dynamodb.model.PutItemRequest

class SaveClickedUrlService(val dynamoDbClient: DynamoDbClient): ISaveClickedUrlContract {
    private val tableName: String = "link-analytics";
    private val log = LoggerFactory.getLogger(SaveClickedUrlService::class.java);

    override fun saveClickedUrl(clickedUrl: ClickedUrl) {
        try {
            log.debug("Message received from queue... Starting processing!");
            val item = mutableMapOf<String, AttributeValue>();

            item["ShortCode"] = AttributeValue.fromS(clickedUrl.shortCode);
            item["ClickTimestamp"] = AttributeValue.fromS(clickedUrl.clickTimestamp);

            val request = PutItemRequest.builder()
                .tableName(tableName)
                .item(item)
                .build();

            dynamoDbClient.putItem(request);
            log.info("Clicked url saved with success!");
        } catch (ex: Exception) {
            log.error("Error to process clicked url message");
            throw BadRequestException(ex.message.toString());
        }
    }
}