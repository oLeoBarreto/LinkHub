package com.leobarreto.Shared.Infrastructure.Queue

import software.amazon.awssdk.services.sqs.model.Message

interface IQueueConsumer {
    fun receiveMessage(): List<Message>;
    fun deleteMessage(message: Message);
}