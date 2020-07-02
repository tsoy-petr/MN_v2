package com.android.hootr.client1c.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.android.hootr.client1c.presentation.MainViewPresentation
import com.android.hootr.client1c.presentation.SettingsViewPresentation
import com.android.hootr.client1c.presentation.ViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(MainViewPresentation::class)
    abstract fun bindMainViewPresentation(mainViewPresentation: MainViewPresentation): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SettingsViewPresentation::class)
    abstract fun bindSettingsViewPresentation(settingsViewPresentation: SettingsViewPresentation): ViewModel
}