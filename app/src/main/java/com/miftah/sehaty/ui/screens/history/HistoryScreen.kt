package com.miftah.sehaty.ui.screens.history

import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.pulltorefresh.PullToRefreshContainer
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.input.nestedscroll.nestedScroll
import eu.bambooapps.material3.pullrefresh.PullRefreshIndicator
import eu.bambooapps.material3.pullrefresh.pullRefresh
import eu.bambooapps.material3.pullrefresh.rememberPullRefreshState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.onEmpty
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HistoryScreen(
    modifier: Modifier = Modifier,
    state: SearchState,
    event: (HistoryEvent) -> Unit,
    navigateToDetail: (Int) -> Unit
) {

    val refreshScope = rememberCoroutineScope()
//    val refreshing = rememberPullToRefreshState()

    val isRefreshing by remember {
        mutableStateOf(false)
    }

    val historyItemsEntity = state.scanHistory?.collectAsLazyPagingItems()

    val refreshing = rememberPullRefreshState(refreshing = isRefreshing, onRefresh = {
        historyItemsEntity?.refresh()
    })

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


        Box(
            modifier = Modifier
                .padding(top = 16.dp)
        ) {
            if (historyItemsEntity?.itemCount == 0 || historyItemsEntity == null) {
                HistoryEmptyScreen(modifier = modifier.pullRefresh(refreshing))
            } else {
                LazyColumn(
                    modifier = modifier.pullRefresh(refreshing),
                    contentPadding = PaddingValues(vertical = 8.dp),
                ) {
                    items(count = historyItemsEntity.itemCount) {
                        historyItemsEntity[it].let { history ->
                            if (history != null) {
                                HistoryCard(
                                    modifier = Modifier
                                        .clickable {
                                            navigateToDetail(history.id!!)
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
                PullRefreshIndicator(
                    refreshing = isRefreshing,
                    state = refreshing,
                    modifier = Modifier
                        .align(Alignment.TopCenter)
                )
            }
        }
    }
}


@Composable
fun SearchHistoryItemsSection(
    modifier: Modifier = Modifier,
    historyItemsEntity: LazyPagingItems<HistoryScannedEntity>?,
    onClick: (Int) -> Unit
) {
    if (historyItemsEntity?.itemCount == 0 || historyItemsEntity == null) {
        HistoryEmptyScreen(modifier = modifier)
    } else {
        LazyColumn(
            modifier = modifier.fillMaxSize(),
            contentPadding = PaddingValues(vertical = 8.dp),
        ) {
            items(count = historyItemsEntity.itemCount) {
                historyItemsEntity[it].let { history ->
                    if (history != null) {
                        HistoryCard(
                            modifier = Modifier
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
    val query by remember { mutableStateOf("") }
    SehatyTheme {
        HistoryScreen(
            state = SearchState(),
            event = {

            },
            navigateToDetail = {

            }
        )
    }
}