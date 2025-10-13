package com.leobarreto.shortener_service.ShorterUrl.Controller

import com.leobarreto.shortener_service.Module.ShorterUrl.Application.Contracts.ICreateShorterUrlContract
import com.leobarreto.shortener_service.Module.ShorterUrl.Application.Contracts.IFindOriginalUrlContract
import com.leobarreto.shortener_service.Module.ShorterUrl.Application.Services.CreateShorterUrlService
import com.leobarreto.shortener_service.Module.ShorterUrl.Application.Services.FindOriginalUrlService
import com.leobarreto.shortener_service.Module.ShorterUrl.Infrastructure.Request.CreateShorterUrlDto
import com.leobarreto.shortener_service.Module.ShorterUrl.Infrastructure.Response.CreateShorterUrlResponseDto
import com.leobarreto.shortener_service.Module.ShorterUrl.Infrastructure.Response.FindOriginalUrlResponseDto
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertInstanceOf
import org.mockito.kotlin.any
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import kotlin.test.assertEquals

class ShorterUrlControllerTest {
    private lateinit var createService: ICreateShorterUrlContract;
    private lateinit var findUrlService: IFindOriginalUrlContract;

    @BeforeEach
    fun setUp() {
        createService = mock<CreateShorterUrlService> {
            on { CreateNewShorterUrl(any<CreateShorterUrlDto>(), any<String>()) } doReturn CreateShorterUrlResponseDto("Key12345")
        }

        findUrlService = mock<FindOriginalUrlService> {
            on { findOriginalUrl(any<String>()) } doReturn FindOriginalUrlResponseDto("http://originalurl.com")
        }
    }

    @Test
    fun tryGetOriginalUrl() {
        val originalUrlResponse = findUrlService.findOriginalUrl("key12345");

        assertInstanceOf<FindOriginalUrlResponseDto>(originalUrlResponse);
        assertEquals("http://originalurl.com", originalUrlResponse.originalUrl);
    }

    @Test
    fun tryPostNewShortUrl() {
        val shorterUrlResponse = createService.CreateNewShorterUrl(CreateShorterUrlDto("http://originalurl.com"), "127.0.0.1");

        assertInstanceOf<CreateShorterUrlResponseDto>(shorterUrlResponse);
        assertEquals("Key12345", shorterUrlResponse.shortId);
        assertEquals(8, shorterUrlResponse.shortId.length);
        assertDoesNotThrow {
            var notPermittedChars = '!'..'/';

            for(i in shorterUrlResponse.shortId.toCharArray().indices) {
                if (shorterUrlResponse.shortId.toCharArray()[i].toChar() in notPermittedChars) {
                    throw Exception();
                }
            }
        }
    }
}