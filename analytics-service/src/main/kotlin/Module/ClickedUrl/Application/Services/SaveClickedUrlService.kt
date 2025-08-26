package com.leobarreto.Module.ClickedUrl.Application.Services

import com.leobarreto.Module.ClickedUrl.Application.Contracts.ISaveClickedUrlContract
import com.leobarreto.Module.ClickedUrl.Domain.ClickedUrl
import software.amazon.awssdk.services.dynamodb.DynamoDbClient
import software.amazon.awssdk.services.dynamodb.model.AttributeValue
import software.amazon.awssdk.services.dynamodb.model.PutItemRequest

class SaveClickedUrlService(val dynamoDbClient: DynamoDbClient): ISaveClickedUrlContract {
    private val tableName: String = "link-analytics";

    override fun saveClickedUrl(clickedUrl: ClickedUrl) {
        val item = mutableMapOf<String, AttributeValue>();

        item["ShortCode"] = AttributeValue.fromS(clickedUrl.shortCode);
        item["ClickTimestamp"] = AttributeValue.fromS(clickedUrl.clickTimestamp);

        val request = PutItemRequest.builder()
            .tableName(tableName)
            .item(item)
            .build();

        dynamoDbClient.putItem(request);
    }
}