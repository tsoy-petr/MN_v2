package com.android.hootor.mn_v2.cache.sync

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.android.hootor.mn_v2.domain.sync.model.*

@Database(
    entities = [TypeOfPriceEntity::class, GoodsItemEntity::class, CategoryEntity::class, PriceListEntity::class, ItemPriceListEntity::class],
    version = 1,
    exportSchema = false
)
abstract class SyncDatabase: RoomDatabase() {

    abstract val syncDao: SyncDao

    companion object{
        @Volatile
        private var INSTANCE: SyncDatabase? = null

        fun getInstance(context: Context): SyncDatabase {
            var instance = INSTANCE

            if (instance == null) {
                instance = Room.databaseBuilder(context.applicationContext, SyncDatabase::class.java, "mn.db")
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
            }
            return instance
        }
    }
}