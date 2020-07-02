package com.android.hootor.ciceronemyapp.di.module

import com.android.hootor.ciceronemyapp.MainActivity
import com.android.hootor.ciceronemyapp.screens.root.RootFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ScreenBindingModule {

    @ContributesAndroidInjector
    abstract fun mainActivity(): MainActivity

    @ContributesAndroidInjector
    abstract fun rootFragment(): RootFragment

}