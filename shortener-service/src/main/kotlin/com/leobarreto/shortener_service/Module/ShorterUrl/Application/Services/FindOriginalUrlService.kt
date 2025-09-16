package com.leobarreto.shortener_service.Module.ShorterUrl.Application.Services

import com.leobarreto.shortener_service.Module.ShorterUrl.Application.Contracts.IFindOriginalUrlContract
import com.leobarreto.shortener_service.Module.ShorterUrl.Infrastructure.Database.ShorterUrlRepository
import com.leobarreto.shortener_service.Module.ShorterUrl.Infrastructure.Response.FindOriginalUrlResponseDto
import lombok.AllArgsConstructor
import lombok.RequiredArgsConstructor
import org.apache.coyote.BadRequestException
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
@RequiredArgsConstructor
@AllArgsConstructor
class FindOriginalUrlService(val repository: ShorterUrlRepository): IFindOriginalUrlContract {
    private val log = LoggerFactory.getLogger(FindOriginalUrlService::class.java);

    override fun findOriginalUrl(shortId: String): FindOriginalUrlResponseDto {
        try {
            log.debug("Starting to find original url.");

            val shorterUrl = repository.findById(shortId).orElseThrow();

            log.info("Original URL found with success!");

            return FindOriginalUrlResponseDto(shorterUrl.originalUrl);
        } catch (ex: Exception) {
            log.error("Error to found original url");
            throw BadRequestException(ex.message);
        }
    }
}