package com.miftah.sehaty.domain.usecase.special

import com.miftah.sehaty.domain.model.FoodAfterScan
import com.miftah.sehaty.domain.repository.AppRepository
import com.miftah.sehaty.utils.UiState
import kotlinx.coroutines.flow.Flow
import java.io.File
import javax.inject.Inject

class GetJWT @Inject constructor(
    private val appRepository: AppRepository
) {
    operator fun invoke(signKey : String): Flow<UiState<String>> {
        return appRepository.getJWT(signKey)
    }
}