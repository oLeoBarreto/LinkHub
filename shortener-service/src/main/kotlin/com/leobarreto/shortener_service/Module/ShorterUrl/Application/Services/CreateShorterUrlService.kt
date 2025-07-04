package com.leobarreto.shortener_service.Module.ShorterUrl.Application.Services

import com.leobarreto.shortener_service.Module.ShorterUrl.Application.Contracts.ICreateShorterUrlContract
import com.leobarreto.shortener_service.Module.ShorterUrl.Infrastructure.Database.ShorterIUrlRepository
import com.leobarreto.shortener_service.Module.ShorterUrl.Infrastructure.Request.CreateShorterUrlDto
import com.leobarreto.shortener_service.Module.ShorterUrl.Infrastructure.Response.CreateShorterUrlResponseDto
import lombok.AllArgsConstructor
import lombok.RequiredArgsConstructor
import org.springframework.stereotype.Service

@Service
@RequiredArgsConstructor
@AllArgsConstructor
class CreateShorterUrlService(val repository: ShorterIUrlRepository): ICreateShorterUrlContract {
    override fun CreateNewShorterUrl(data: CreateShorterUrlDto): CreateShorterUrlResponseDto {
        var alphabet: List<Char> = ('a'..'z') + ('A'..'Z') + ('0'..'9')
        var generatedId: String = generateRandomString(8,alphabet);

        repository.save(generatedId, data.originalUrl);

        return CreateShorterUrlResponseDto(generatedId);
    }

    private fun generateRandomString(length: Int, charSet: List<Char>): String {
        val random = kotlin.random.Random.Default
        return List(length) { charSet.random(random) }.joinToString("")
    }

}