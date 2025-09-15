package com.leobarreto.shortener_service.Module.ShorterUrl.Domain

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import org.hibernate.annotations.CreationTimestamp
import java.sql.Timestamp

@Entity
@Table(name = "ShorterUrl")
data class ShorterUrl(@Id var shortId: String, var originalUrl: String) {
    @CreationTimestamp
    lateinit var shortedAt: Timestamp;
    lateinit var shortedByIP: String;
}
