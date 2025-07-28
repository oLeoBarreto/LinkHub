package com.leobarreto.shortener_service.Module.ShorterUrl.Application.Services

import com.leobarreto.shortener_service.Module.ShorterUrl.Application.Contracts.IFindOriginalUrlContract
import com.leobarreto.shortener_service.Module.ShorterUrl.Infrastructure.Database.ShorterUrlRepository
import com.leobarreto.shortener_service.Module.ShorterUrl.Infrastructure.Response.FindOriginalUrlResponseDto
import lombok.AllArgsConstructor
import lombok.RequiredArgsConstructor
import org.springframework.stereotype.Service

@Service
@RequiredArgsConstructor
@AllArgsConstructor
class FindOriginalUrlService(val repository: ShorterUrlRepository): IFindOriginalUrlContract {
    override fun findOriginalUrl(shortId: String): FindOriginalUrlResponseDto {
        val shorterUrl = repository.findById(shortId).orElseThrow();

        return FindOriginalUrlResponseDto(shorterUrl.originalUrl);
    }
}