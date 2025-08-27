package com.leobarreto.shortener_service.ShorterUrl.Service

import com.leobarreto.shortener_service.Module.ShorterUrl.Application.Contracts.IFindOriginalUrlContract
import com.leobarreto.shortener_service.Module.ShorterUrl.Application.Services.FindOriginalUrlService
import com.leobarreto.shortener_service.Module.ShorterUrl.Domain.ShorterUrl
import com.leobarreto.shortener_service.Module.ShorterUrl.Infrastructure.Database.InMemory.ShorterUrlInMemoryRepository
import com.leobarreto.shortener_service.Module.ShorterUrl.Infrastructure.Database.ShorterUrlRepository
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertNotNull
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.any
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import java.util.Optional
import kotlin.test.assertEquals
import kotlin.test.assertFails

class FindOriginalUrlServiceTest {
    private val repository = mock<ShorterUrlRepository>();
    private val findOriginalUrlService: IFindOriginalUrlContract = FindOriginalUrlService(repository);

    @BeforeEach
    fun setUp() {
        whenever(repository.findById("key123")).thenReturn(Optional.of<ShorterUrl>(ShorterUrl("key123", "http://originalurl.com")));
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