package com.leobarreto.shortener_service.Module.ShorterUrl.Domain

import jakarta.persistence.Entity

@Entity
data class ShorterUrl(val originalUrl: String, val shortId: String) {}
