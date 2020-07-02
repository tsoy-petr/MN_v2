package com.android.hootr.testcicerone.fr_3

import io.reactivex.Single
import java.util.*

class SeriesInteractor(
    val repo: ICreatSeriesRepositories = CreatSeriesRepositories()
) {
    fun getItems(): List<Series> = repo.getItems(UUID.randomUUID().toString())

    fun getSeries(): Single<List<Series>> = repo.getSeries(UUID.randomUUID().toString())

}

