package com.miftah.sehaty.data.remote.retrofit

import com.miftah.sehaty.data.remote.dto.FoodScannedRequest
import okhttp3.MultipartBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface ApiService {
    @POST("api/images/upload")
    suspend fun scanNutrition(@Part image: MultipartBody.Part)

    @POST("api/whatsapp/generate-code")
    suspend fun generateJWT(@Body signKey : String)

    @GET("api/whatsapp/check-session")
    suspend fun checkSession()

    @Multipart
    @POST("api/product/add-history")
    suspend fun addHistory(
        @Part("food") food: MultipartBody.Part,
        @Part photo: MultipartBody.Part
    )

    @GET("api/product/get-history")
    suspend fun getHistory()

    @GET("api/product/detail-history?index=3")
    suspend fun detailHistory()
}