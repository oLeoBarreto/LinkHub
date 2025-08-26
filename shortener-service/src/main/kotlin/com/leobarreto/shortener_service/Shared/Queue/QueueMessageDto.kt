package com.leobarreto.shortener_service.Shared.Queue

import kotlinx.serialization.Serializable

@Serializable
data class QueueMessageDto(val shortCode: String, val clickTimestamp: String)