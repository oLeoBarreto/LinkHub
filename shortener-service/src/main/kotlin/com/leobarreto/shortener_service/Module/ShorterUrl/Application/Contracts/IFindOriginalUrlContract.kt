package com.leobarreto.shortener_service.Module.ShorterUrl.Application.Contracts

import com.leobarreto.shortener_service.Module.ShorterUrl.Infrastructure.Response.FindOriginalUrlResponseDto

interface IFindOriginalUrlContract {
    fun findOriginalUrl(shortId: String): FindOriginalUrlResponseDto;
}