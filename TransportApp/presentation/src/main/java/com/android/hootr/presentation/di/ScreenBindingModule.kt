package com.android.hootr.presentation.di

import com.android.hootr.domen.id.modules.UseCasesModule
import com.android.hootr.presentation.screens.countries.CountriesFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module(includes = [UseCasesModule::class])
abstract class ScreenBindingModule {

    @ContributesAndroidInjector
    abstract fun countriesFragment(): CountriesFragment
}