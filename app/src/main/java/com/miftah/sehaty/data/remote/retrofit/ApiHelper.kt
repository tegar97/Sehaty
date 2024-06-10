package com.miftah.sehaty.data.remote.retrofit

import com.miftah.sehaty.data.remote.dto.FoodScannedRequest
import com.miftah.sehaty.data.remote.dto.NutritionRequest
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.Part

interface ApiHelper {

    suspend fun scanNutrition(@Part image: MultipartBody.Part)

    suspend fun generateJWT(@Body signKey : String)

    suspend fun checkSession()

    suspend fun addHistory(
        @Part("name") name: RequestBody,
        @Part photo: MultipartBody.Part,
        @Part("nutrition") nutrition: NutritionRequest
    )

    suspend fun getHistory()

    suspend fun detailHistory()
}