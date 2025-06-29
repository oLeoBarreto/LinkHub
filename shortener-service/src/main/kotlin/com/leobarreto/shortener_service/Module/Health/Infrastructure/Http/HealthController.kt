package com.leobarreto.shortener_service.Module.Health.Infrastructure.Http

import com.leobarreto.shortener_service.Module.Health.Domain.HealthStatus
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Controller
@RestController
@RequestMapping("/health")
class HealthController: IHealthEndpoints {
    @GetMapping()
    override fun getHealthStatus(): HealthStatus {
        return HealthStatus("Up");
    }
}