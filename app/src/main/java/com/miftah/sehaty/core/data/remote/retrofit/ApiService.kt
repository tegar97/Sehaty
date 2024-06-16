package com.miftah.sehaty.core.data.remote.retrofit

import com.miftah.sehaty.core.data.remote.dto.request.NutritionRequest
import com.miftah.sehaty.core.data.remote.dto.response.CheckResponse
import com.miftah.sehaty.core.data.remote.dto.response.FoodSaveResponse
import com.miftah.sehaty.core.data.remote.dto.response.GenerateResponse
import com.miftah.sehaty.core.data.remote.dto.response.GetHistoryResponse
import com.miftah.sehaty.core.data.remote.dto.response.ScanNutritionResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface ApiService {
    @POST("api/images/upload")
    suspend fun scanNutrition(@Part image: MultipartBody.Part): ScanNutritionResponse

    @POST("api/whatsapp/generate-code")
    suspend fun generateJWT(@Body signKey: String): GenerateResponse

    @GET("api/whatsapp/check-session")
    suspend fun checkSession(): CheckResponse

    @Multipart
    @POST("api/product/add-history")
    suspend fun addHistory(
        @Part("name") name: RequestBody,
        @Part photo: MultipartBody.Part,
        @Part("nutrition") nutrition: NutritionRequest
    ): FoodSaveResponse

    @GET("api/product/get-history")
    suspend fun getHistory(): GetHistoryResponse
}