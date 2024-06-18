package com.miftah.sehaty.ui.screens.history

import androidx.paging.PagingData
import com.miftah.sehaty.core.data.local.entity.HistoryScannedEntity
import kotlinx.coroutines.flow.Flow

data class SearchState(
    val searchQuery: String = "",
    val scanHistory: Flow<PagingData<HistoryScannedEntity>>? = null
)
