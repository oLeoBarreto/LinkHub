package com.leobarreto.shortener_service.ShorterUrl.Service

import com.leobarreto.shortener_service.Module.ShorterUrl.Application.Contracts.ICreateShorterUrlContract
import com.leobarreto.shortener_service.Module.ShorterUrl.Application.Services.CreateShorterUrlService
import com.leobarreto.shortener_service.Module.ShorterUrl.Domain.ShorterUrl
import com.leobarreto.shortener_service.Module.ShorterUrl.Infrastructure.Database.InMemory.ShorterUrlInMemoryRepository
import com.leobarreto.shortener_service.Module.ShorterUrl.Infrastructure.Database.ShorterUrlRepository
import com.leobarreto.shortener_service.Module.ShorterUrl.Infrastructure.Request.CreateShorterUrlDto
import com.leobarreto.shortener_service.Module.ShorterUrl.Infrastructure.Response.CreateShorterUrlResponseDto
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertInstanceOf
import org.junit.jupiter.api.assertNotNull
import org.junit.jupiter.api.assertDoesNotThrow
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.any
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import java.util.Optional
import kotlin.test.assertEquals

class CreateShorterUrlTest {
    private val repository = mock<ShorterUrlRepository>();
    private val createShorterUrl: ICreateShorterUrlContract = CreateShorterUrlService(repository);

    @BeforeEach
    fun setUp() {
        whenever(repository.save<ShorterUrl>(any<ShorterUrl>())).thenReturn(ShorterUrl("key123", "http://originalurl.com"))
    }

    @Test
    fun tryCreateNewShortUrl() {
        val newShortUrl = createShorterUrl.CreateNewShorterUrl(CreateShorterUrlDto("http://originalurl.com"));
        assertNotNull(newShortUrl);
        assertInstanceOf<CreateShorterUrlResponseDto>(newShortUrl);
    }

    @Test
    fun verifyGeneratedKey() {
        val newShortUrl = createShorterUrl.CreateNewShorterUrl(CreateShorterUrlDto("http://originalurl.com"));

        assertEquals(8, newShortUrl.shortId.length);
        assertDoesNotThrow {
            var notPermittedChars = '!'..'/';

            for(i in newShortUrl.shortId.toCharArray().indices) {
                if (newShortUrl.shortId.toCharArray()[i].toChar() in notPermittedChars) {
                    throw Exception();
                }
            }
        }
    }
}