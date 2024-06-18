package com.miftah.sehaty.ui.screens.onboarding

import com.miftah.sehaty.utils.UiState
import kotlinx.coroutines.flow.Flow

data class OnBoardingState(
    val generateJWT : Flow<UiState<String>>? = null,
)
