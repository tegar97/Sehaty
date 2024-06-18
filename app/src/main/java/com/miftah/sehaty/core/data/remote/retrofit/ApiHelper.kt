package com.miftah.sehaty.core.data.remote.retrofit

import com.miftah.sehaty.core.data.remote.dto.request.NutritionRequest
import com.miftah.sehaty.core.data.remote.dto.request.SignKeyRequest
import com.miftah.sehaty.core.data.remote.dto.response.CheckResponse
import com.miftah.sehaty.core.data.remote.dto.response.FoodSaveResponse
import com.miftah.sehaty.core.data.remote.dto.response.GenerateResponse
import com.miftah.sehaty.core.data.remote.dto.response.GetHistoryResponse
import com.miftah.sehaty.core.data.remote.dto.response.ScanNutritionResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody

interface ApiHelper {

    suspend fun scanNutrition(image: MultipartBody.Part): ScanNutritionResponse

    suspend fun generateJWT(signKeyRequest: SignKeyRequest): GenerateResponse

    suspend fun checkSession(): CheckResponse

    suspend fun addHistory(
        name: RequestBody,
        photo: MultipartBody.Part,
        nutrition: NutritionRequest
    ): FoodSaveResponse

    suspend fun getHistory(): GetHistoryResponse
}