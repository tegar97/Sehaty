package com.miftah.sehaty.ui.screens.history

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.miftah.sehaty.domain.model.HistoryScanned
import com.miftah.sehaty.ui.screens.common.ChipAndWarning
import com.miftah.sehaty.ui.screens.common.MainSearchBar
import com.miftah.sehaty.ui.screens.history.components.HistoryCard
import com.miftah.sehaty.ui.theme.SehatyTheme
import com.miftah.sehaty.ui.theme.dimens

@Composable
fun HistoryScreen(
    modifier: Modifier = Modifier,
    titleScreen: String,
    query: String,
    historyScanned: List<HistoryScanned>,
    onQueryChange: ((String) -> Unit),
    onSearch: ((String) -> Unit),
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {
        Text(
            modifier = Modifier.padding(top = MaterialTheme.dimens.small1),
            text = titleScreen,
            style = MaterialTheme.typography.titleLarge
        )
        MainSearchBar(
            modifier = Modifier.padding(top = MaterialTheme.dimens.medium1),
            isActive = false,
            query = query,
            onSearch = onSearch,
            onQueryChange = onQueryChange
        )
        LazyColumn(
            contentPadding = PaddingValues(vertical = 8.dp)
        ) {
            itemsIndexed(historyScanned) { index, item ->
                HistoryCard(
                    modifier = Modifier.padding(8.dp),
                    urlImage = item.productPhoto,
                    itemName = item.productName,
                    itemsChip = item.warnings.map {
                        ChipAndWarning(it, Color.Red, Color.Red)
                    }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun HistoryScreenPreview() {
    /*val query by remember { mutableStateOf("") }
    val dummy = dummyHistoriesScanned(15)
    SehatyTheme {
        HistoryScreen(
            titleScreen = "Scan History",
            query = query,
            historyScanned = dummy,
            onQueryChange = {

            },
            onSearch = {

            }
        )
    }*/
}