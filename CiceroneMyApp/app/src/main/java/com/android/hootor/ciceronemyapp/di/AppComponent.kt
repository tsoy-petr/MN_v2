package com.android.hootor.ciceronemyapp.di

import android.app.Application
import com.android.hootor.ciceronemyapp.App
import com.android.hootor.ciceronemyapp.di.module.NavigationModule
import com.android.hootor.ciceronemyapp.di.module.ScreenBindingModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector

@Component(
    modules = [
        AndroidInjectionModule::class,
        ScreenBindingModule::class,
        NavigationModule::class
    ]
)
@AppScope
interface AppComponent : AndroidInjector<App> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): AppComponent.Builder
        fun build(): AppComponent
    }
}