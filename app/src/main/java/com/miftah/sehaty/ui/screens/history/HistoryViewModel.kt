package com.miftah.sehaty.ui.screens.history

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.miftah.sehaty.domain.usecase.history.GetAllHistoryScanned
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HistoryViewModel @Inject constructor(
    private val getAllHistoryScanned: GetAllHistoryScanned
) : ViewModel() {
    private var _state = mutableStateOf(SearchState())
    val state: State<SearchState> get() = _state

    fun onEvent(event: HistoryEvent) {
        when (event) {
            HistoryEvent.SearchNews -> {
                searchHistory()
            }

            is HistoryEvent.UpdateSearchQuery -> {
                _state.value = _state.value.copy(
                    searchQuery = event.searchQuery
                )
            }
        }
    }

    private fun searchHistory() {
        val scanHistory = getAllHistoryScanned(
            search = _state.value.searchQuery
        ).cachedIn(viewModelScope)
        _state.value = _state.value.copy(
            scanHistory = scanHistory
        )
    }
}