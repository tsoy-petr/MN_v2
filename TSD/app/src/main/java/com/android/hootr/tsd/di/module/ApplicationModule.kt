package com.android.hootr.tsd.di.module

import android.app.Application
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationModule(private val app: Application) {

    @Singleton
    @Provides
    fun provideApplicationContext(): Application {
        return app
    }



}