package com.leobarreto.shortener_service

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ShortenerServiceApplication

fun main(args: Array<String>) {
	runApplication<ShortenerServiceApplication>(*args)
}
