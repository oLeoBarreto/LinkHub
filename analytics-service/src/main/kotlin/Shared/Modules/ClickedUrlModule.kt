package com.leobarreto.Shared.Modules

import com.leobarreto.Module.ClickedUrl.Application.Services.SaveClickedUrlService
import org.koin.dsl.module

var ClickedUrlModule = module {
    single { SaveClickedUrlService(get()) }
}