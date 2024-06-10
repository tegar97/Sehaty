package com.miftah.sehaty.data.repository

import com.miftah.sehaty.data.remote.retrofit.ApiService
import com.miftah.sehaty.domain.repository.AppRepository
import javax.inject.Inject

class AppRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
) : AppRepository {

}