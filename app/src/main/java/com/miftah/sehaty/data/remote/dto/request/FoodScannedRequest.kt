package com.miftah.sehaty.data.remote.dto.request

import com.miftah.sehaty.domain.model.ImageScan
import okhttp3.RequestBody

data class FoodScannedRequest(
    val name: RequestBody,
    val nutrition: NutritionRequest,
    val photo: ImageScan
)