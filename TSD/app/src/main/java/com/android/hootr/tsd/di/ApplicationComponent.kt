package com.android.hootr.tsd.di

import com.android.hootr.tsd.di.module.ApplicationModule
import com.android.hootr.tsd.di.module.PrefsModule
import com.android.hootr.tsd.screens.LaunchActivity
import com.android.hootr.tsd.screens.SettingsActivity
import com.android.hootr.tsd.screens.login.LoginActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class, PrefsModule::class])
interface ApplicationComponent {

//    fun getApplication(): Application
//
//    fun getPrefs(): Prefs
    fun inject(activity: SettingsActivity)
    fun inject(activity: LaunchActivity)
    fun inject(activity: LoginActivity)
}