package com.leobarreto.shortener_service.Module.ShorterUrl.Application.Contracts

import com.leobarreto.shortener_service.Module.ShorterUrl.Infrastructure.Request.CreateShorterUrlDto
import com.leobarreto.shortener_service.Module.ShorterUrl.Infrastructure.Response.CreateShorterUrlResponseDto

interface ICreateShorterUrlContract {
    fun CreateNewShorterUrl(data: CreateShorterUrlDto): CreateShorterUrlResponseDto;
}