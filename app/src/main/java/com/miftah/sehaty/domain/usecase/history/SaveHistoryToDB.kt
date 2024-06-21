package com.miftah.sehaty.domain.usecase.history

import com.miftah.sehaty.domain.model.FoodForCloud
import com.miftah.sehaty.domain.model.HistoryScanned
import com.miftah.sehaty.domain.repository.AppRepository
import com.miftah.sehaty.utils.UiState
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SaveHistoryToDB @Inject constructor(
    private val appRepository: AppRepository
) {
    suspend operator fun invoke(historyScanned: HistoryScanned): Flow<UiState<String>> {
        return appRepository.saveHistoryToDB(historyScanned)
    }
}