package com.leobarreto.shortener_service.Module.ShorterUrl.Domain

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "ShorterUrl")
data class ShorterUrl(@Id var shortId: String, var originalUrl: String) {}
