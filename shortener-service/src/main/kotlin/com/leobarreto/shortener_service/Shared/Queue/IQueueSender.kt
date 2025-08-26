package com.leobarreto.shortener_service.Shared.Queue

interface IQueueSender {
    fun sendMessage(message: QueueMessageDto);
}