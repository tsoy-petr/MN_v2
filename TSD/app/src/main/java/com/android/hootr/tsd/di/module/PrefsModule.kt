package com.android.hootr.tsd.di.module

import android.app.Application
import com.android.hootr.tsd.prefs.PrefsImp
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [ApplicationModule::class])
class PrefsModule {

    @Singleton
    @Provides
    fun providePrefs(application: Application): PrefsImp {
        return PrefsImp(application.applicationContext)
    }
}