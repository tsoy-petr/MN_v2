package com.android.hootor.mn_v2.presentation.injection

import android.app.Application
import android.content.Context
import com.android.hootor.mn_v2.remote.SyncRemote
import com.android.hootor.mn_v2.remote.SyncRemoteImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
 class AppModule(private val context: Context) {

    @Provides
    @Singleton
    fun provideAppContext(): Context = context



}