package com.leobarreto.Module.ClickedUrl.Infrastructure.Utils

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.leobarreto.Module.ClickedUrl.Domain.ClickedUrl

fun parseToClickedUrl(message: String): ClickedUrl {
    val parsedObject: ClickedUrl = jacksonObjectMapper().readValue(message);
    return parsedObject;
}