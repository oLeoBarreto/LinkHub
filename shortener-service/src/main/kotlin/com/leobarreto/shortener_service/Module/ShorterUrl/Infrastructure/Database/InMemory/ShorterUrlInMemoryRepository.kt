package com.leobarreto.shortener_service.Module.ShorterUrl.Infrastructure.Database.InMemory

import com.leobarreto.shortener_service.Module.ShorterUrl.Domain.ShorterUrl
import com.leobarreto.shortener_service.Module.ShorterUrl.Infrastructure.Database.ShorterUrlRepository
import org.springframework.stereotype.Component
import java.util.Optional
import java.util.concurrent.ConcurrentHashMap

@Component
class ShorterUrlInMemoryRepository {
    private var repository: ConcurrentHashMap<String, String> = ConcurrentHashMap<String, String>();

    fun save(key: String, originalUrl: String): ShorterUrl {
        repository[key] = originalUrl;
        return ShorterUrl(originalUrl, key);
    }

    fun findByShortId(shortId: String): ShorterUrl {
        return ShorterUrl(repository.getValue(shortId), shortId);
    }

    fun <S : ShorterUrl?> save(entity: S & Any): S & Any {
        repository[entity.shortId] = entity.originalUrl;
        return ShorterUrl(entity.shortId, entity.originalUrl) as (S & Any);
    }

    fun findById(id: String): Optional<ShorterUrl> {
        val originalUrl = repository.getValue(id)

        return if (originalUrl.isNotEmpty()) {
            Optional.of(ShorterUrl(id, originalUrl));
        } else {
            Optional.ofNullable(null);
        }
    }
}