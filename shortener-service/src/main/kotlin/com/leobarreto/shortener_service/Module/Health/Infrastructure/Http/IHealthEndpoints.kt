package com.leobarreto.shortener_service.Module.Health.Infrastructure.Http

import com.leobarreto.shortener_service.Module.Health.Domain.HealthStatus

interface IHealthEndpoints {
    fun getHealthStatus(): HealthStatus;
}