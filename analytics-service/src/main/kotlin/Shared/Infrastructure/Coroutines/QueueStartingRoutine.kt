package com.leobarreto.Shared.Infrastructure.Coroutines

import com.leobarreto.Shared.Infrastructure.Queue.SqsQueueConsumer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Semaphore
import kotlinx.coroutines.sync.withPermit

class QueueStartingRoutine(var sqsQueueConsumer: SqsQueueConsumer) {
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
                            println("Message received: " + message.body());
                            sqsQueueConsumer.deleteMessage(message);
                        }
                    }
                }
            }
        }
    }
}