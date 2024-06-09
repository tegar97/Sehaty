package com.miftah.sehaty.ui.screens.scan

import android.graphics.Bitmap
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class ScanViewModel {

    private val _bitmaps = MutableStateFlow<List<Bitmap>>(emptyList())
    val bitmaps = _bitmaps.asStateFlow()

    fun onTakePhoto(bitmap: Bitmap) {
        _bitmaps.value += bitmap
    }
}