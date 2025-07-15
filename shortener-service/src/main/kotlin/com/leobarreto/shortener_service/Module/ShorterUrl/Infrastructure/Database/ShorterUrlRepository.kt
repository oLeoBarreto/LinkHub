package com.leobarreto.shortener_service.Module.ShorterUrl.Infrastructure.Database

import com.leobarreto.shortener_service.Module.ShorterUrl.Domain.ShorterUrl
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface ShorterUrlRepository: CrudRepository<ShorterUrl, String> {
}