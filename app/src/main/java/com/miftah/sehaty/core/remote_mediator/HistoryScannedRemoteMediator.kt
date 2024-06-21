package com.miftah.sehaty.core.remote_mediator

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.miftah.sehaty.core.data.local.entity.HistoryScannedEntity
import com.miftah.sehaty.core.data.local.entity.RemoteKeysEntity
import com.miftah.sehaty.core.data.local.entity.convertHistoryScannedEntity
import com.miftah.sehaty.core.data.local.room.AppDatabase
import com.miftah.sehaty.core.data.remote.retrofit.ApiService
import kotlinx.coroutines.flow.Flow
import retrofit2.HttpException
import java.util.ArrayList

@OptIn(ExperimentalPagingApi::class)
class HistoryScannedRemoteMediator(
    private val apiService: ApiService,
    private val appDatabase: AppDatabase,
    private val search: String?,
    private val isActive: Boolean
) : RemoteMediator<Int, HistoryScannedEntity>() {
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, HistoryScannedEntity>
    ): MediatorResult {
        val page = when (loadType) {
            LoadType.REFRESH -> {
                val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                remoteKeys?.nextKey?.minus(1) ?: INITIAL_PAGE_INDEX
            }

            LoadType.PREPEND -> {
                val remoteKeys = getRemoteKeyForFirstItem(state)
                val prevKey = remoteKeys?.prevKey
                    ?: return MediatorResult.Success(endOfPaginationReached = remoteKeys != null)
                prevKey
            }

            LoadType.APPEND -> {
                val remoteKeys = getRemoteKeyForLastItem(state)
                val nextKey = remoteKeys?.nextKey
                    ?: return MediatorResult.Success(endOfPaginationReached = remoteKeys != null)
                nextKey
            }
        }

        try {
            val responseData: List<HistoryScannedEntity> = if (isActive) {
                if (search.isNullOrEmpty()) {
                    apiService.getHistory().data.map {
                        it.convertHistoryScannedEntity()
                    }
                } else {
                    appDatabase.historyScannedDao().searchItem(search)
                }
            } else {
                if (search.isNullOrEmpty()) {
                    appDatabase.historyScannedDao().getAllHistoriesScanned()
                } else {
                    appDatabase.historyScannedDao().searchItem(search)
                }
            }

            val endOfPaginationReached = responseData.isEmpty()

            appDatabase.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    appDatabase.remoteKeysDao().deleteRemoteKeys()
                    appDatabase.historyScannedDao().deleteAllHistoryScanned()
                }
                val prevKey = if (page == 1) null else page - 1
                val nextKey = if (endOfPaginationReached) null else page + 1
                val keys = responseData.map {
                    RemoteKeysEntity(id = it.id, prevKey = prevKey, nextKey = nextKey)
                }
                appDatabase.remoteKeysDao().insertAll(keys)
                if (isActive) appDatabase.historyScannedDao().insertHistoriesScanned(responseData)
            }
            return MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
        } catch (exception: Exception) {
            return MediatorResult.Error(exception)
        } catch (exception: HttpException) {
            return MediatorResult.Error(exception)
        }
    }

    override suspend fun initialize(): InitializeAction {
        return if (isActive) InitializeAction.LAUNCH_INITIAL_REFRESH else InitializeAction.SKIP_INITIAL_REFRESH
    }

    private suspend fun getRemoteKeyForLastItem(state: PagingState<Int, HistoryScannedEntity>): RemoteKeysEntity? {
        return state.pages.lastOrNull { it.data.isNotEmpty() }?.data?.lastOrNull()?.let { data ->
            appDatabase.remoteKeysDao().getRemoteKeysId(data.id)
        }
    }

    private suspend fun getRemoteKeyForFirstItem(state: PagingState<Int, HistoryScannedEntity>): RemoteKeysEntity? {
        return state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()?.let { data ->
            appDatabase.remoteKeysDao().getRemoteKeysId(data.id)
        }
    }

    private suspend fun getRemoteKeyClosestToCurrentPosition(state: PagingState<Int, HistoryScannedEntity>): RemoteKeysEntity? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { id ->
                appDatabase.remoteKeysDao().getRemoteKeysId(id)
            }
        }
    }

    private companion object {
        const val INITIAL_PAGE_INDEX = 1
    }

}