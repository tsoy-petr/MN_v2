package com.android.hootor.mn_v2.presentation.injection

import android.content.Context
import com.android.hootor.mn_v2.cache.sync.SyncDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


class CacheModule {


    fun provideDatabase(context: Context): SyncDatabase {
        return SyncDatabase.getInstance(context)
    }
}