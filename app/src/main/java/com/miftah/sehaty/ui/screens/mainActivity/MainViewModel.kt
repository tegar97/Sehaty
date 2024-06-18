package com.miftah.sehaty.ui.screens.mainActivity

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.miftah.sehaty.domain.usecase.app_entry.CheckEntry
import com.miftah.sehaty.ui.screens.navGraph.Route
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val readAppEntry : CheckEntry
): ViewModel() {

    private val _currentDestination = mutableStateOf(Route.OnBoardingApp.route)
    val currentDestination: State<String> = _currentDestination

    val visiblePermissionDialogQueue = mutableStateListOf<String>()
    var isPermissionAcquire = mutableStateOf(false)

    fun dismissDialog() {
        visiblePermissionDialogQueue.removeFirst()
    }

    fun onPermissionResult(
        permission: String,
        isGranted: Boolean
    ) {
        if(!isGranted && !visiblePermissionDialogQueue.contains(permission)) {
            visiblePermissionDialogQueue.add(permission)
        } else {
            isPermissionAcquire.value = true
        }
    }

    init {
        readAppEntry().onEach { shouldStartFromHomeScreen ->
            if(shouldStartFromHomeScreen){
                _currentDestination.value = Route.AppStartNavigation.route
            }else{
                _currentDestination.value = Route.OnBoardingApp.route
            }
            delay(300) //Without this delay, the onBoarding screen will show for a momentum.
        }.launchIn(viewModelScope)
    }
}