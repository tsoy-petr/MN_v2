package com.android.hootor.mn_v2.presentation.injection

import android.content.Context
import com.android.hootor.mn_v2.cache.sync.SyncCache
import com.android.hootor.mn_v2.cache.sync.SyncDatabase
import com.android.hootor.mn_v2.remote.SyncRemote
import com.android.hootor.mn_v2.remote.SyncRemoteImpl
import com.android.hootor.mn_v2.ui.synchronization.SyncRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class SyncModule {

    @Singleton
    @Provides
     fun provideSyncRemote(): SyncRemote = SyncRemoteImpl()

    @Singleton
    @Provides
    fun provideDatabase(context: Context): SyncDatabase {
        return SyncDatabase.getInstance(context)
    }

    @Singleton
    @Provides
    fun provideSyncRepository(remote: SyncRemote, syncDatabase: SyncDatabase): SyncRepository = SyncRepository(remote, syncDatabase)
}