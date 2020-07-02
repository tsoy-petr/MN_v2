package com.android.hootr.client1c.di

import android.content.Context
import com.android.hootr.client1c.interactor.ImpMainInteractor
import com.android.hootr.client1c.interactor.MainInteractor
import com.android.hootr.client1c.prefs.SharedPrefsManager
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private val context: Context) {

    @Provides
    @Singleton
    fun provideAppContext(): Context = context

    @Provides
    @Singleton
    fun provideMainInteractor(prefs: SharedPrefsManager): MainInteractor {
        return ImpMainInteractor(prefs)
    }

}