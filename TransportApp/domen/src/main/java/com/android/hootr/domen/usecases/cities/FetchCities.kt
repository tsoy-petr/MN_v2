package com.android.hootr.domen.usecases.cities

import com.android.hootr.domen.usecases.UseCase

class FetchCities: UseCase<Unit, String> {
    override suspend fun execute(
        request: Unit?,
        onSuccess: (String) -> Unit,
        onFailure: (String) -> Unit
    ) {
        onSuccess.invoke("Cities count 6")
    }

}