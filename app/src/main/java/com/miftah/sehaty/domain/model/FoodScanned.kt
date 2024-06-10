package com.miftah.sehaty.domain.model

import com.miftah.sehaty.data.remote.dto.FoodScannedRequest
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File

data class FoodScanned(
    val nutrition: Nutrition,
    val name: String,
    val photo: File
)

fun FoodScanned.convertToFoodRequest(): FoodScannedRequest {
    val name = this.name.toRequestBody("text/plain".toMediaTypeOrNull())

    return FoodScannedRequest(
        nutrition = nutrition.convertToNutritionRequest(),
        name = name,
        photo = this.photo.convertToImageScan()
    )
}
