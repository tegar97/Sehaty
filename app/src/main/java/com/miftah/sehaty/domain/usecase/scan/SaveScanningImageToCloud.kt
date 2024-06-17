package com.miftah.sehaty.domain.usecase.scan

import com.miftah.sehaty.domain.model.FoodAfterScan
import com.miftah.sehaty.domain.model.FoodForCloud
import com.miftah.sehaty.domain.repository.AppRepository
import com.miftah.sehaty.utils.UiState
import kotlinx.coroutines.flow.Flow
import java.io.File
import javax.inject.Inject

class SaveScanningImageToCloud @Inject constructor(
    private val appRepository: AppRepository
) {
    operator fun invoke(foodForCloud: FoodForCloud): Flow<UiState<String>> {
        return appRepository.saveScanHistoryToCloud(foodForCloud)
    }
}