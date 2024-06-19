package com.miftah.sehaty.ui.screens.scan

import android.graphics.Bitmap
import android.net.Uri
import com.miftah.sehaty.domain.model.FoodAfterScan
import com.miftah.sehaty.utils.UiState
import kotlinx.coroutines.flow.Flow

data class ScanState(
    val imageTitle: String = "",
    val imageUri: Uri? = null,
    val imageBitmap: Bitmap? = null,
    val saveScanImageResult : Flow<UiState<String>>? = null,
    val scanImageResult : Flow<UiState<FoodAfterScan>>? = null,
    val sendFoodForCloud : Flow<UiState<String>>? = null,
)