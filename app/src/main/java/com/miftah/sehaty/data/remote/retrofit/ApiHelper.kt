package com.miftah.sehaty.data.remote.retrofit

import com.miftah.sehaty.data.remote.dto.request.NutritionRequest
import com.miftah.sehaty.data.remote.dto.response.CheckResponse
import com.miftah.sehaty.data.remote.dto.response.DetailHistoryResponse
import com.miftah.sehaty.data.remote.dto.response.GenerateResponse
import com.miftah.sehaty.data.remote.dto.response.GetHistoryResponse
import com.miftah.sehaty.data.remote.dto.response.HistorySaveResponse
import com.miftah.sehaty.data.remote.dto.response.ScanNutritionResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.Part

interface ApiHelper {

    suspend fun scanNutrition(image: MultipartBody.Part): ScanNutritionResponse

    suspend fun generateJWT(signKey: String): GenerateResponse

    suspend fun checkSession(): CheckResponse

    suspend fun addHistory(
        name: RequestBody,
        photo: MultipartBody.Part,
        nutrition: NutritionRequest
    ): HistorySaveResponse

    suspend fun getHistory(): DetailHistoryResponse

    suspend fun detailHistory(index: Int): GetHistoryResponse
}