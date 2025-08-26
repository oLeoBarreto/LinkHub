package com.leobarreto.Shared.Infrastructure.Coroutines

import com.leobarreto.Module.ClickedUrl.Application.Services.SaveClickedUrlService
import com.leobarreto.Module.ClickedUrl.Infrastructure.Utils.parseToClickedUrl
import com.leobarreto.Shared.Infrastructure.Queue.SqsQueueConsumer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Semaphore
import kotlinx.coroutines.sync.withPermit
import software.amazon.awssdk.services.sqs.model.Message

class QueueStartingRoutine(var sqsQueueConsumer: SqsQueueConsumer, var saveClickedUrlService: SaveClickedUrlService) {
    private val semaphore: Semaphore = Semaphore(10); //Create semaphore to control a max of 10 threads at time.

    fun startConsuming(scope: CoroutineScope) {
        scope.launch(Dispatchers.IO) { // Dispatchers.IO is a dispatcher thread definition, using the I/O operation.
            while (isActive) {
                val messages = sqsQueueConsumer.receiveMessage();

                if (messages.isEmpty()) {
                    delay(1000);
                    continue
                }

                messages.forEach {message ->
                    launch {
                        semaphore.withPermit {
                            processMessage(message);
                        }
                    }
                }
            }
        }
    }

    private fun processMessage(message: Message) {
        val parsedMessage = parseToClickedUrl(message.body());
        saveClickedUrlService.saveClickedUrl(parsedMessage);

        sqsQueueConsumer.deleteMessage(message);

        println("Message received and processed: $parsedMessage");
    }
}