package com.miftah.sehaty.domain.usecase.special

import com.miftah.sehaty.domain.repository.AppRepository
import com.miftah.sehaty.utils.UiState
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CheckSessionWA @Inject constructor(
    private val appRepository: AppRepository
) {
    operator fun invoke(signKey : String): Flow<UiState<String>> {
        return appRepository.checkSessionWA()
    }
}