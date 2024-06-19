package com.miftah.sehaty.ui.screens.detail

import android.content.Context
import android.os.Build
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.miftah.sehaty.domain.model.FoodAfterScan
import com.miftah.sehaty.domain.usecase.history.GetAllHistoryScanned
import com.miftah.sehaty.domain.usecase.scan.SendScanningImage
import com.miftah.sehaty.ui.screens.history.SearchState
import com.miftah.sehaty.utils.reduceFileImage
import com.miftah.sehaty.utils.reduceFileImageCompat
import com.miftah.sehaty.utils.uriToFile
import dagger.hilt.android.lifecycle.HiltViewModel
import java.lang.ref.WeakReference
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    val sendScanningImage: SendScanningImage
) : ViewModel() {
    private var _detailState = mutableStateOf(DetailState())
    val detailState: State<DetailState> get() = _detailState

    private var contextRef: WeakReference<Context>? = null

    fun onEvent(event: DetailEvent) {

    }

    fun setDetailState(foodAfterScan: FoodAfterScan) {
        _detailState.value = _detailState.value.copy(
            foodAfterScan = foodAfterScan
        )
    }

   /* private fun sendScanningNutrientToCloud() {
        val imageFile = uriToFile(, contextRef?.get()!!)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            imageFile.reduceFileImage()
//                Log.d("URI -> FILE", imageFile.path)
            _detailState.value = _detailState.value.copy(
                sendFoodForCloud = sendScanningImage()
            )
        } else {
            val reducedFile = imageFile.reduceFileImageCompat()
//                Log.d("URI -> FILE (Compat)", reducedFile.path)
            _detailState.value = _detailState.value.copy(
                sendFoodForCloud = sendScanningImage()
            )
        }

    }*/
}