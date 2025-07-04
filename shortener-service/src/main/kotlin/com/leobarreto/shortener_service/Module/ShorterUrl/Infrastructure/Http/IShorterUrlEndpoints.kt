package com.leobarreto.shortener_service.Module.ShorterUrl.Infrastructure.Http

import com.leobarreto.shortener_service.Module.ShorterUrl.Infrastructure.Request.CreateShorterUrlDto
import com.leobarreto.shortener_service.Module.ShorterUrl.Infrastructure.Response.CreateShorterUrlResponseDto
import com.leobarreto.shortener_service.Module.ShorterUrl.Infrastructure.Response.FindOriginalUrlResponseDto

interface IShorterUrlEndpoints {
    fun postNewShorterUrl(data: CreateShorterUrlDto): CreateShorterUrlResponseDto;
    fun getOriginalUrl(shortId: String): FindOriginalUrlResponseDto;
}