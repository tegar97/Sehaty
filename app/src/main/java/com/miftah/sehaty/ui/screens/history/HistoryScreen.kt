package com.miftah.sehaty.ui.screens.history

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.miftah.sehaty.ui.screens.common.MainSearchBar
import com.miftah.sehaty.ui.theme.SehatyTheme
import com.miftah.sehaty.ui.theme.dimens
import retrofit2.http.Query

@Composable
fun HistoryScreen(
    modifier: Modifier = Modifier,
    titleScreen: String,
    query: String,
    onQueryChange: ((String) -> Unit),
    onSearch: ((String) -> Unit),
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {
        Spacer(
            modifier = modifier
                .fillMaxWidth()
                .height(MaterialTheme.dimens.small2)
        )
        Text(
            text = titleScreen,
            style = MaterialTheme.typography.titleLarge
        )
        Spacer(
            modifier = modifier
                .fillMaxWidth()
                .height(MaterialTheme.dimens.medium1)
        )
        MainSearchBar(
            activity = false,
            query = query,
            onSearch = onSearch,
            onQueryChange = onQueryChange
        )

    }
}

@Preview(showBackground = true)
@Composable
private fun HistoryScreenPreview() {
    val query by remember { mutableStateOf("") }
    SehatyTheme {
        HistoryScreen(
            titleScreen = "Scan History",
            query = query,
            onQueryChange = {

            },
            onSearch = {

            }
        )
    }
}