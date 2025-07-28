package com.leobarreto.shortener_service.ShorterUrl.Service

import com.leobarreto.shortener_service.Module.ShorterUrl.Application.Contracts.IFindOriginalUrlContract
import com.leobarreto.shortener_service.Module.ShorterUrl.Application.Services.FindOriginalUrlService
import com.leobarreto.shortener_service.Module.ShorterUrl.Domain.ShorterUrl
import com.leobarreto.shortener_service.Module.ShorterUrl.Infrastructure.Database.InMemory.ShorterUrlInMemoryRepository
import com.leobarreto.shortener_service.Module.ShorterUrl.Infrastructure.Database.ShorterUrlRepository
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertNotNull
import kotlin.test.assertEquals
import kotlin.test.assertFails

class FindOriginalUrlServiceTest {
    private val repository: ShorterUrlRepository = ShorterUrlInMemoryRepository()
    private val findOriginalUrlService: IFindOriginalUrlContract = FindOriginalUrlService(repository);

    @BeforeEach
    fun setUp() {
        repository.save(ShorterUrl("key123", "http://originalurl.com"));
    }

    @Test
    fun tryFindOriginalUrl() {
        val originalUrl = findOriginalUrlService.findOriginalUrl("key123");
        assertNotNull(originalUrl);
        assertEquals("http://originalurl.com", originalUrl.originalUrl);
    }

    @Test
    fun tryFailWhenNotFindOriginalUrl() {
        assertFails() {
            findOriginalUrlService.findOriginalUrl("key321");
        }
    }
}