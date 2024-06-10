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
import javax.inject.Inject

class ApiServiceImpl @Inject constructor(
    private val apiService: ApiService
) : ApiHelper {
    override suspend fun scanNutrition(image: MultipartBody.Part): ScanNutritionResponse =
        apiService.scanNutrition(image)

    override suspend fun generateJWT(signKey: String): GenerateResponse =
        apiService.generateJWT(signKey)

    override suspend fun checkSession(): CheckResponse =
        apiService.checkSession()

    override suspend fun addHistory(
        name: RequestBody,
        photo: MultipartBody.Part,
        nutrition: NutritionRequest
    ): HistorySaveResponse =
        apiService.addHistory(name, photo, nutrition)

    override suspend fun getHistory(): DetailHistoryResponse =
        apiService.getHistory()

    override suspend fun detailHistory(index: Int): GetHistoryResponse =
        apiService.detailHistory(index)

}