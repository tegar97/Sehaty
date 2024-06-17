package com.miftah.sehaty.domain.usecase.history

import com.miftah.sehaty.domain.model.HistoryScanned
import com.miftah.sehaty.domain.preference.UserPreference
import com.miftah.sehaty.domain.repository.AppRepository
import com.miftah.sehaty.utils.UiState
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetHistoryDetailByIndex @Inject constructor(
    private val appRepository: AppRepository
) {
    operator fun invoke(index: Int): Flow<UiState<HistoryScanned>> {
        return appRepository.getDetailHistoryByIndex(index)
    }
}