package com.leobarreto.Module.ClickedUrl.Application.Contracts

import com.leobarreto.Module.ClickedUrl.Domain.ClickedUrl

interface ISaveClickedUrlContract {
    fun saveClickedUrl(clickedUrl: ClickedUrl);
}