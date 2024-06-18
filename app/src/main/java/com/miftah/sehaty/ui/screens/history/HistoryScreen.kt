package com.miftah.sehaty.ui.screens.history

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.miftah.sehaty.core.data.local.entity.HistoryScannedEntity
import com.miftah.sehaty.domain.model.HistoryScanned
import com.miftah.sehaty.ui.screens.common.ChipAndWarning
import com.miftah.sehaty.ui.screens.common.MainSearchBar
import com.miftah.sehaty.ui.screens.history.components.HistoryCard
import com.miftah.sehaty.ui.theme.SehatyTheme
import com.miftah.sehaty.ui.theme.dimens
import com.miftah.sehaty.utils.AppUtility.fromStringToList
import androidx.compose.foundation.lazy.items

@Composable
fun HistoryScreen(
    modifier: Modifier = Modifier,
    state: SearchState,
    event: (HistoryEvent) -> Unit,
    navigateToDetail: (Int) -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {
        Text(
            modifier = Modifier.padding(top = MaterialTheme.dimens.small1),
            text = "Scan History",
            style = MaterialTheme.typography.titleLarge
        )
        MainSearchBar(
            modifier = Modifier.padding(top = MaterialTheme.dimens.medium1),
            isActive = false,
            query = state.searchQuery,
            onSearch = {},
            onQueryChange = {
                event(HistoryEvent.UpdateSearchQuery(it))
            }
        )
        state.scanHistory?.let {
            SearchHistoryItemsSection(
                modifier = Modifier.padding(top = 8.dp),
                historyItemsEntity = it.collectAsLazyPagingItems(),
                onClick = navigateToDetail
            )
        }
    }
}

@Composable
fun SearchHistoryItemsSection(
    modifier: Modifier = Modifier,
    historyItemsEntity: LazyPagingItems<HistoryScannedEntity>,
    onClick: (Int) -> Unit
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(vertical = 8.dp)
    ) {
        items(count = historyItemsEntity.itemCount) {
            historyItemsEntity[it].let { history ->
                if (history != null) {
                    HistoryCard(
                        modifier = Modifier
                            .padding(8.dp)
                            .clickable {
                                onClick(history.id!!)
                            },
                        urlImage = history.productPhoto,
                        itemName = history.productName,
                        itemsChip = fromStringToList(history.warnings).map { text ->
                            ChipAndWarning(text, Color.Red, Color.White)
                        }
                    )
                }
            }
        }
    }
}

/*@Composable
fun handlePagingResult(articles: LazyPagingItems<HistoryScannedEntity>): Boolean {
    val loadState = articles.loadState
    val error = when {
        loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
        loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
        loadState.append is LoadState.Error -> loadState.append as LoadState.Error
        else -> null
    }

    return when {
        loadState.refresh is LoadState.Loading -> {
            ShimmerEffect()
            false
        }

        error != null -> {
            EmptyScreen(error = error)
            false
        }

        else -> {
            true
        }
    }
}

@Composable
fun ShimmerEffect() {
    Column(verticalArrangement = Arrangement.spacedBy(MediumPadding1)) {
        repeat(10) {
            ArticleCardShimmerEffect(
                modifier = Modifier.padding(horizontal = MediumPadding1)
            )
        }
    }
}*/

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