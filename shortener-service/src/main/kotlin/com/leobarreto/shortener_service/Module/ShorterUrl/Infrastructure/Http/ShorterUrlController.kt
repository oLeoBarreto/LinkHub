package com.leobarreto.shortener_service.Module.ShorterUrl.Infrastructure.Http

import com.leobarreto.shortener_service.Module.ShorterUrl.Application.Contracts.ICreateShorterUrlContract
import com.leobarreto.shortener_service.Module.ShorterUrl.Application.Contracts.IFindOriginalUrlContract
import com.leobarreto.shortener_service.Module.ShorterUrl.Infrastructure.Request.CreateShorterUrlDto
import com.leobarreto.shortener_service.Module.ShorterUrl.Infrastructure.Response.CreateShorterUrlResponseDto
import com.leobarreto.shortener_service.Module.ShorterUrl.Infrastructure.Response.FindOriginalUrlResponseDto
import com.leobarreto.shortener_service.Shared.Queue.IQueueSender
import com.leobarreto.shortener_service.Shared.Queue.QueueMessageDto
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDateTime

@Controller
@RestController
@RequestMapping("/shorterUrl")
class ShorterUrlController (val createService: ICreateShorterUrlContract, val findUrlService: IFindOriginalUrlContract, val queueSender: IQueueSender): IShorterUrlEndpoints {

    @PostMapping("/links")
    @ResponseStatus(HttpStatus.OK)
    override fun postNewShorterUrl(@RequestBody data: CreateShorterUrlDto): CreateShorterUrlResponseDto {
        return createService.CreateNewShorterUrl(data);
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.FOUND)
    override fun getOriginalUrl(@RequestParam shortId: String): FindOriginalUrlResponseDto {
        queueSender.sendMessage(QueueMessageDto(shortId, LocalDateTime.now().toString()));
        return findUrlService.findOriginalUrl(shortId);
    }
}