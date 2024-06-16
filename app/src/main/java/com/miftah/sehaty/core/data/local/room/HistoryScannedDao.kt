package com.miftah.sehaty.core.data.local.room

import androidx.paging.PagingSource
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.miftah.sehaty.core.data.local.entity.HistoryScannedEntity

interface HistoryScannedDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHistoryScanned(movie: List<HistoryScannedEntity>)

    @Query("SELECT * FROM historyScanItem")
    fun getAllHistoryScanned(): PagingSource<Int, HistoryScannedEntity>

    @Query("DELETE FROM historyScanItem")
    suspend fun deleteAllHistoryScanned()

    @Query("SELECT * FROM historyScanItem WHERE idItem = :idItem")
    suspend fun findHistoryScanItemWithID(idItem: Int) : HistoryScannedEntity

    @Query("SELECT * FROM historyScanItem WHERE productName LIKE :search")
    fun searchItem(search: String?): List<HistoryScannedEntity>
}