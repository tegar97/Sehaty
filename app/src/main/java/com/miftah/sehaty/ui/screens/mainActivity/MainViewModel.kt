package com.miftah.sehaty.ui.screens.mainActivity

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
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
}