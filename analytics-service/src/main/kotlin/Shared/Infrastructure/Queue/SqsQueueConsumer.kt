package com.leobarreto.Shared.Infrastructure.Queue

import software.amazon.awssdk.services.sqs.SqsClient
import software.amazon.awssdk.services.sqs.model.DeleteMessageRequest
import software.amazon.awssdk.services.sqs.model.GetQueueUrlRequest
import software.amazon.awssdk.services.sqs.model.Message
import software.amazon.awssdk.services.sqs.model.ReceiveMessageRequest

class SqsQueueConsumer(var sqsClient: SqsClient) {
    private val queueName: String = "link-clicked-queue.fifo";

    fun receiveMessage() {
        val receiveMessageRequest = ReceiveMessageRequest.builder()
            .queueUrl(getQueueUrl())
            .build();

        val messages: List<Message> = sqsClient.receiveMessage(receiveMessageRequest).messages();

        for (message in messages) {
            println("Message received: " + message.body());
            deleteProcessedMessage(message);
        }
    }

    private fun getQueueUrl(): String {
        val getQueueRequest = GetQueueUrlRequest.builder()
            .queueName(queueName)
            .build();

        val getQueueResponse = sqsClient.getQueueUrl(getQueueRequest);
        return getQueueResponse.queueUrl();
    }

    private fun deleteProcessedMessage(message: Message) {
        val messageId = message.messageId();
        val deleteMessageRequest = DeleteMessageRequest.builder()
            .queueUrl(getQueueUrl())
            .receiptHandle(message.receiptHandle())
            .build();

        try {
            sqsClient.deleteMessage(deleteMessageRequest);
            println("Message deleted with ID: " + messageId);
        } catch (e: Exception) {
            println("Failed to delete message: " + e);
        }
    }
}