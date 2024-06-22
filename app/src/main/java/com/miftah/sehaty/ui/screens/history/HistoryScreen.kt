package com.miftah.sehaty.ui.screens.history

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.miftah.sehaty.domain.model.convertToHistoryScanned
import com.miftah.sehaty.ui.screens.common.SearchBarSehatySecondary
import com.miftah.sehaty.ui.screens.common.setSimpleScore
import com.miftah.sehaty.ui.screens.history.components.HistoriesCard
import com.miftah.sehaty.ui.theme.Grey30
import com.miftah.sehaty.ui.theme.Grey50
import eu.bambooapps.material3.pullrefresh.PullRefreshIndicator
import eu.bambooapps.material3.pullrefresh.pullRefresh
import eu.bambooapps.material3.pullrefresh.rememberPullRefreshState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.onEmpty
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun HistoryScreen(
    modifier: Modifier = Modifier,
    state: SearchState,
    event: (HistoryEvent) -> Unit,
    navigateToDetail: (HistoryScanned) -> Unit
) {
    val refreshScope = rememberCoroutineScope()

    val isRefreshing by remember {
        mutableStateOf(false)
    }

    val historyItemsEntity = state.scanHistory?.collectAsLazyPagingItems()

    val refreshing = rememberPullRefreshState(
        refreshing = isRefreshing,
        onRefresh = {
            historyItemsEntity?.refresh()
        }
    )

    LaunchedEffect(state.searchQuery) {
        event(HistoryEvent.SearchNews)
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {
        LazyColumn(
            modifier = modifier
                .pullRefresh(refreshing)
                .fillMaxSize(),
            contentPadding = PaddingValues(vertical = 8.dp),
        ) {
            stickyHeader {
                SearchBarSehatySecondary(
                    modifier = Modifier
                        .padding(
                            bottom = MaterialTheme.dimens.small2
                        )
                        .fillMaxWidth(),
                    query = state.searchQuery
                ) {
                    event(HistoryEvent.SearchNews)
                }
            }
            items(count = historyItemsEntity?.itemCount?:0) {
                historyItemsEntity?.get(it)?.let { history ->
                    HistoriesCard(
                        modifier = Modifier
                            .clickable {
                                navigateToDetail(history.convertToHistoryScanned())
                            },
                        urlImage = history.productPhoto,
                        productName = history.productName,
                        itemsChip = fromStringToList(history.warnings).map { text ->
                            ChipAndWarning(text, Color.Red, Color.White)
                        },
                        simpleScoreData = setSimpleScore(history.grade)
                    )
                    Spacer(modifier = Modifier.padding(bottom = 8.dp))
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

    if (historyItemsEntity?.itemCount == 0 || historyItemsEntity == null || historyItemsEntity.loadState.hasError) {
        /*Box(
            modifier = modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
        ) {
            LazyColumn(
                modifier = modifier
                    .pullRefresh(refreshing)
                    .fillMaxSize(),
                contentPadding = PaddingValues(vertical = 8.dp),
            ) {
                item {
                    SearchBarSehatySecondary(
                        modifier = Modifier
                            .padding(
                                bottom = MaterialTheme.dimens.small2
                            )
                            .fillMaxWidth(),
                        query = state.searchQuery
                    ) {
                        event(HistoryEvent.SearchNews)
                    }
                }
            }

            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                if (historyItemsEntity?.loadState?.hasError == true) {
                    Text(text = "Something wrong like your life")
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(text = "Pull to refresh")
                } else {
                    Text(text = "Its empty like your life")
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(text = "Pull to refresh")
                }

            }

            PullRefreshIndicator(
                refreshing = isRefreshing,
                state = refreshing,
                modifier = Modifier
                    .align(Alignment.TopCenter)
            )
        }*/
    } else {
        /*Box(
            modifier = modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
        ) {
            LazyColumn(
                modifier = modifier
                    .pullRefresh(refreshing)
                    .fillMaxSize(),
                contentPadding = PaddingValues(vertical = 8.dp),
            ) {
                stickyHeader {
                    SearchBarSehatySecondary(
                        modifier = Modifier
                            .padding(
                                bottom = MaterialTheme.dimens.small2
                            )
                            .fillMaxWidth(),
                        query = state.searchQuery
                    ) {
                        event(HistoryEvent.SearchNews)
                    }
                }
                items(count = historyItemsEntity.itemCount) {
                    historyItemsEntity[it].let { history ->
                        history?.let {
                            HistoriesCard(
                                modifier = Modifier
                                    .padding(bottom = 8.dp)
                                    .clickable {
                                        navigateToDetail(history.convertToHistoryScanned())
                                    },
                                urlImage = history.productPhoto,
                                productName = history.productName,
                                itemsChip = fromStringToList(history.warnings).map { text ->
                                    ChipAndWarning(text, Color.Red, Color.White)
                                },
                                simpleScoreData = setSimpleScore(history.grade)
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
        }*/
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
                                    onClick(history.id)
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