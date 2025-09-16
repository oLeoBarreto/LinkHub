package com.leobarreto.shortener_service.Module.ShorterUrl.Application.Services

import com.leobarreto.shortener_service.Module.ShorterUrl.Application.Contracts.ICreateShorterUrlContract
import com.leobarreto.shortener_service.Module.ShorterUrl.Domain.ShorterUrl
import com.leobarreto.shortener_service.Module.ShorterUrl.Infrastructure.Database.ShorterUrlRepository
import com.leobarreto.shortener_service.Module.ShorterUrl.Infrastructure.Request.CreateShorterUrlDto
import com.leobarreto.shortener_service.Module.ShorterUrl.Infrastructure.Response.CreateShorterUrlResponseDto
import lombok.AllArgsConstructor
import lombok.RequiredArgsConstructor
import org.apache.coyote.BadRequestException
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
@RequiredArgsConstructor
@AllArgsConstructor
class CreateShorterUrlService(val repository: ShorterUrlRepository): ICreateShorterUrlContract {
    private val log = LoggerFactory.getLogger(CreateShorterUrlService::class.java);

    override fun CreateNewShorterUrl(data: CreateShorterUrlDto, shortedByIP: String): CreateShorterUrlResponseDto {
        try {
            log.debug("Starting to generate short url identification.");

            val alphabet: List<Char> = ('a'..'z') + ('A'..'Z') + ('0'..'9')
            val generatedId: String = generateRandomString(8,alphabet);

            log.debug("Short identification generated.");

            val shorterUrl = ShorterUrl(generatedId, data.originalUrl);
            shorterUrl.shortedByIP = shortedByIP;
            repository.save(shorterUrl);

            log.info("Shorter URL generated and saved!");

            return CreateShorterUrlResponseDto(generatedId);
        } catch (ex: Exception) {
            log.error("Error to create a short url.");
            throw BadRequestException(ex.message);
        }
    }

    private fun generateRandomString(length: Int, charSet: List<Char>): String {
        val random = kotlin.random.Random.Default;
        return List(length) { charSet.random(random) }.joinToString("");
    }

}