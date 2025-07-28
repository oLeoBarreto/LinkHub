package com.leobarreto.shortener_service.Module.ShorterUrl.Infrastructure.Database.InMemory

import com.leobarreto.shortener_service.Module.ShorterUrl.Domain.ShorterUrl
import com.leobarreto.shortener_service.Module.ShorterUrl.Infrastructure.Database.ShorterUrlRepository
import org.springframework.stereotype.Component
import java.util.Optional
import java.util.concurrent.ConcurrentHashMap

@Component
class ShorterUrlInMemoryRepository: ShorterUrlRepository {
    private var repository: ConcurrentHashMap<String, String> = ConcurrentHashMap<String, String>();

    fun save(key: String, originalUrl: String): ShorterUrl {
        repository[key] = originalUrl;
        return ShorterUrl(originalUrl, key);
    }

    fun findByShortId(shortId: String): ShorterUrl {
        return ShorterUrl(repository.getValue(shortId), shortId);
    }

    override fun <S : ShorterUrl?> save(entity: S & Any): S & Any {
        repository[entity.shortId] = entity.originalUrl;
        return ShorterUrl(entity.shortId, entity.originalUrl) as (S & Any);
    }

    override fun <S : ShorterUrl?> saveAll(entities: Iterable<S?>): Iterable<S?> {
        TODO("Not yet implemented")
    }

    override fun findById(id: String): Optional<ShorterUrl> {
        val originalUrl = repository.getValue(id)

        return if (originalUrl.isNotEmpty()) {
            Optional.of(ShorterUrl(id, originalUrl));
        } else {
            Optional.ofNullable(null);
        }
    }

    override fun existsById(id: String): Boolean {
        TODO("Not yet implemented")
    }

    override fun findAll(): Iterable<ShorterUrl?> {
        TODO("Not yet implemented")
    }

    override fun findAllById(ids: Iterable<String?>): Iterable<ShorterUrl?> {
        TODO("Not yet implemented")
    }

    override fun count(): Long {
        TODO("Not yet implemented")
    }

    override fun deleteById(id: String) {
        TODO("Not yet implemented")
    }

    override fun delete(entity: ShorterUrl) {
        TODO("Not yet implemented")
    }

    override fun deleteAllById(ids: Iterable<String?>) {
        TODO("Not yet implemented")
    }

    override fun deleteAll(entities: Iterable<ShorterUrl?>) {
        TODO("Not yet implemented")
    }

    override fun deleteAll() {
        TODO("Not yet implemented")
    }
}