package com.miftah.sehaty.core.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.miftah.sehaty.core.data.local.entity.HistoryScannedEntity
import com.miftah.sehaty.core.data.local.entity.RemoteKeysEntity


@Database(
    entities = [HistoryScannedEntity::class, RemoteKeysEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun historyScannedDao() : HistoryScannedDao

    abstract fun remoteKeysDao() : RemoteKeysDao
}