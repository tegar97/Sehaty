package com.miftah.sehaty.ui.screens.scan

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.provider.Settings
import android.util.Log
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.miftah.sehaty.utils.reduceFileImage
import com.miftah.sehaty.utils.reduceFileImageCompat
import com.miftah.sehaty.utils.uriToFile
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.lang.ref.WeakReference
import javax.inject.Inject


@HiltViewModel
class ScanViewModel @Inject constructor(): ViewModel() {

    private val _imageBitmap = MutableStateFlow<Bitmap?>(null)
    val bitmaps = _imageBitmap.asStateFlow()

    private val _imageUri = MutableStateFlow<Uri?>(null)
    val imageUri = _imageUri.asStateFlow()

    private val _visiblePermissionDialogQueue = mutableStateListOf<String>()
    val visiblePermissionDialogQueue = _visiblePermissionDialogQueue.toList()

    private var _isPermissionAcquire = MutableStateFlow(false)
    val isPermissionAcquire = _isPermissionAcquire.asStateFlow()

    private var _itemName = MutableStateFlow("")
    val itemName = _itemName.asStateFlow()

    private var contextRef: WeakReference<Context>? = null

    fun onEvent(event: ScanEvent) {
        when (event) {
            is ScanEvent.SetContextWeakReference -> {
                contextRef = WeakReference(event.context)
            }

            is ScanEvent.SaveToUri -> {
                saveToUri(event.uri)
            }

            is ScanEvent.WriteItemTitle -> {
                _itemName.value = event.text
            }
        }
    }

    fun dismissDialog(navController: NavHostController) {
        navController.popBackStack()
        if (!_visiblePermissionDialogQueue.isEmpty()) {
            _visiblePermissionDialogQueue.removeFirst()
        } else {
            navController.popBackStack()
        }
    }

    fun okPressed() {
        if (!_visiblePermissionDialogQueue.isEmpty()) {
            _visiblePermissionDialogQueue.removeFirst()
        }
    }

    fun onPermissionResult(
        permission: String,
        isGranted: Boolean
    ) {
        if (!isGranted && !_visiblePermissionDialogQueue.contains(permission)) {
            _visiblePermissionDialogQueue.add(permission)
        } else {
            _isPermissionAcquire.value = true
        }
    }

    private fun saveToUri(uri: Uri? = null) {
        _imageUri.value = uri
        if ((uri != null) || (contextRef?.get() != null)) {
            val imageFile = uriToFile(uri!!, contextRef?.get()!!)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                imageFile.reduceFileImage()
                Log.d("URI -> FILE", imageFile.path)
            } else {
                val reducedFile = imageFile.reduceFileImageCompat()
                Log.d("URI -> FILE (Compat)", reducedFile.path)
            }
        }
    }
}