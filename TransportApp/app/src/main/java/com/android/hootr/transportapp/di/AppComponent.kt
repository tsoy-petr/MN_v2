package com.android.hootr.transportapp.di

import android.app.Application
import com.android.hootr.presentation.di.PresentationComponent
import com.android.hootr.presentation.di.ScreenBindingModule
import com.android.hootr.transportapp.TransportApp
import com.android.hootr.transportapp.di.modules.ActivityBindingModule
import com.android.hootr.utilities.di.UtilsComponent
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector


@Component(
    modules = [
        AndroidInjectionModule::class,
        ActivityBindingModule::class,
        ScreenBindingModule::class
    ],
    dependencies = [PresentationComponent::class, UtilsComponent::class]
)
@AppScope
interface AppComponent: AndroidInjector<TransportApp> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): AppComponent.Builder

        fun presentationComponent(presentationComponent: PresentationComponent): AppComponent.Builder
        fun utilsComponent(utilsComponent: UtilsComponent): AppComponent.Builder

        fun build(): AppComponent
    }
}