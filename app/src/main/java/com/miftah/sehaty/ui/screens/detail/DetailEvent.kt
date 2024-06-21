package com.miftah.sehaty.ui.screens.detail

import android.content.Context
import com.miftah.sehaty.ui.screens.scan.ScanEvent

sealed class DetailEvent {

    data object SaveToCloud : DetailEvent()

    data object SaveToLocal : DetailEvent()
}