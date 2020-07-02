package com.android.hootr.domen.usecases.countries

import com.android.hootr.domen.usecases.UseCase
import com.android.hootr.domen.usecases.countries.models.CountryModel

class FetchCountries: UseCase<Unit, List<CountryModel>> {

    override suspend fun execute(
        request: Unit?,
        onSuccess: (List<CountryModel>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        onSuccess.invoke(
            listOf(
                CountryModel(title = "Austria"),
                CountryModel(title = "Australia")
            )
        )
    }

}