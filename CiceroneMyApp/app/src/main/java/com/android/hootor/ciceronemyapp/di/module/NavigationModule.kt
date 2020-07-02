package com.android.hootor.ciceronemyapp.di.module

import com.android.hootor.ciceronemyapp.MainActivity
import com.android.hootor.ciceronemyapp.di.AppScope
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.Router
import javax.inject.Singleton

@Module
abstract class NavigationModule {

    @Module
    companion object {

        val cicerone: Cicerone<Router> = Cicerone.create()

        @Provides
        @JvmStatic
        fun provideRouter(): Router = cicerone.router

        @Provides
        @JvmStatic
        fun provideNavigatorHolder() = cicerone.navigatorHolder

    }

}