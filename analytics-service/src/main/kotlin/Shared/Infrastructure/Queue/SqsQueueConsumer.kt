package com.leobarreto.Shared.Infrastructure.Queue

import software.amazon.awssdk.services.sqs.SqsClient
import software.amazon.awssdk.services.sqs.model.DeleteMessageRequest
import software.amazon.awssdk.services.sqs.model.GetQueueUrlRequest
import software.amazon.awssdk.services.sqs.model.Message
import software.amazon.awssdk.services.sqs.model.ReceiveMessageRequest

class SqsQueueConsumer(var sqsClient: SqsClient): IQueueConsumer {
    private val queueName: String = "link-clicked-queue.fifo";

    override fun receiveMessage(): List<Message> {
        val receiveMessageRequest = ReceiveMessageRequest.builder()
            .queueUrl(getQueueUrl())
            .waitTimeSeconds(20)
            .maxNumberOfMessages(10)
            .build();

        val messages: List<Message> = sqsClient.receiveMessage(receiveMessageRequest).messages();

        return messages;
    }

    override fun deleteMessage(message: Message) {
        val deleteMessageRequest = DeleteMessageRequest.builder()
            .queueUrl(getQueueUrl())
            .receiptHandle(message.receiptHandle())
            .build();

        try {
            sqsClient.deleteMessage(deleteMessageRequest);
        } catch (e: Exception) {
            throw Exception("Failed to delete message: " + e)
        }
    }

    private fun getQueueUrl(): String {
        val getQueueRequest = GetQueueUrlRequest.builder()
            .queueName(queueName)
            .build();

        val getQueueResponse = sqsClient.getQueueUrl(getQueueRequest);
        return getQueueResponse.queueUrl();
    }
}