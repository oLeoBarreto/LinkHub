package com.leobarreto.shortener_service.Module.ShorterUrl.Application.Services

import com.leobarreto.shortener_service.Module.ShorterUrl.Application.Contracts.IFindOriginalUrlContract
import com.leobarreto.shortener_service.Module.ShorterUrl.Infrastructure.Database.ShorterIUrlRepository
import com.leobarreto.shortener_service.Module.ShorterUrl.Infrastructure.Response.FindOriginalUrlResponseDto
import lombok.AllArgsConstructor
import lombok.RequiredArgsConstructor
import org.springframework.stereotype.Service

@Service
@RequiredArgsConstructor
@AllArgsConstructor
class FindOriginalUrlService(val repository: ShorterIUrlRepository): IFindOriginalUrlContract {
    override fun findOriginalUrl(shortId: String): FindOriginalUrlResponseDto {
        var shorterUrl = repository.findByShortId(shortId);

        return FindOriginalUrlResponseDto(shorterUrl.originalUrl);
    }
}