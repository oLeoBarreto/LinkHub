package com.leobarreto.shortener_service.Shared.Queue

import kotlinx.serialization.json.Json
import org.springframework.stereotype.Component
import software.amazon.awssdk.services.sqs.SqsClient
import software.amazon.awssdk.services.sqs.model.GetQueueUrlRequest
import software.amazon.awssdk.services.sqs.model.SendMessageRequest

@Component
class SqsQueueSender(val sqsClient: SqsClient): IQueueSender {
    private val queueName: String = "link-clicked-queue.fifo";

    override fun sendMessage(message: QueueMessageDto) {
        val request = SendMessageRequest.builder()
            .queueUrl(getQueueUrl())
            .messageBody(Json.encodeToString(QueueMessageDto.serializer(), message))
            .messageGroupId("ShortenerService")
            .messageDeduplicationId(message.shortCode)
            .build();

        sqsClient.sendMessage(request);
    }

    private fun getQueueUrl(): String {
        val getQueueRequest = GetQueueUrlRequest.builder()
            .queueName(queueName)
            .build();

        val getQueueResponse = sqsClient.getQueueUrl(getQueueRequest);
        return getQueueResponse.queueUrl();
    }
}