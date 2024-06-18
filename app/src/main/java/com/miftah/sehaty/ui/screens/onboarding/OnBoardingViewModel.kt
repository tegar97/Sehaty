package com.miftah.sehaty.ui.screens.onboarding

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.miftah.sehaty.domain.usecase.app_entry.SaveEntry
import com.miftah.sehaty.domain.usecase.special.GetJWT
import com.miftah.sehaty.utils.AppUtility.getRandomString
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class OnBoardingViewModel @Inject constructor(
    private val saveEntry: SaveEntry,
    private val getJWT: GetJWT
) : ViewModel() {

    private val _onBoardingState = mutableStateOf(OnBoardingState())
    val onBoardingState: State<OnBoardingState>
        get() = _onBoardingState

    fun onEvent(event: OnBoardingEvent) {
        when (event) {
            OnBoardingEvent.GenerateJWT -> {
                generateJWT()
            }
            is OnBoardingEvent.SaveAppJWT -> {
                saveUserEntry(event.saveJWT)
            }
        }
    }

    private fun saveUserEntry(saveJWT: String) {
        viewModelScope.launch {
            saveEntry(saveJWT)
        }
    }

    private fun generateJWT() {
        _onBoardingState.value = _onBoardingState.value.copy(
            generateJWT = getJWT(getRandomString(10))
        )
    }

}