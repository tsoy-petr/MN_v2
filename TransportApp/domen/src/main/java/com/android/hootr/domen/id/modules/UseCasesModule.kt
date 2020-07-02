package com.android.hootr.domen.id.modules

import com.android.hootr.domen.usecases.cities.FetchCities
import com.android.hootr.domen.usecases.countries.FetchCountries
import dagger.Module
import dagger.Provides

@Module
class UseCasesModule {

    @Provides
    fun provideFetchCountriesUseCase(): FetchCountries {
        return FetchCountries()
    }

    @Provides
    fun provideFetchCitiesUseCase(): FetchCities {
        return FetchCities()
    }
}