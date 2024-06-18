package com.miftah.sehaty.ui.screens.onboarding

sealed class OnBoardingEvent {

    data class SaveAppJWT(val saveJWT: String): OnBoardingEvent()

    data object GenerateJWT : OnBoardingEvent()
}
