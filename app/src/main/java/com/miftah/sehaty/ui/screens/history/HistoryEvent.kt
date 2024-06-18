package com.miftah.sehaty.ui.screens.history

sealed class HistoryEvent {
    data class UpdateSearchQuery(val searchQuery: String) : HistoryEvent()

    data object SearchNews : HistoryEvent()
}