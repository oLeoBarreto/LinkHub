package com.leobarreto.shortener_service.Module.ShorterUrl.Infrastructure.Database

import com.leobarreto.shortener_service.Module.ShorterUrl.Domain.ShorterUrl
import org.springframework.stereotype.Component
import java.util.concurrent.ConcurrentHashMap

@Component
class ShorterIUrlRepository {
    var repository: ConcurrentHashMap<String, String> = ConcurrentHashMap<String, String>();

    fun save(key: String, originalUrl: String): ShorterUrl {
        repository[key] = originalUrl;
        return ShorterUrl(originalUrl, key);
    }

    fun findByShortId(shortId: String): ShorterUrl {
        return ShorterUrl(repository.getValue(shortId), shortId);
    }
}