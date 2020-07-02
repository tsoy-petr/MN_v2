package com.android.hootr.transportapp.di.modules

import com.android.hootr.transportapp.screens.main.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingModule {

    @ContributesAndroidInjector
    abstract fun mainActivity(): MainActivity
}