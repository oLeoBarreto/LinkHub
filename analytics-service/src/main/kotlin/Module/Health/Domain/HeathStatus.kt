package com.leobarreto.Module.Health.Domain

import kotlinx.serialization.Serializable

@Serializable
data class HeathStatus(val status: String) {
}