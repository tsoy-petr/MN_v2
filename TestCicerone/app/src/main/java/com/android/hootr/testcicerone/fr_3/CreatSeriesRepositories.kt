package com.android.hootr.testcicerone.fr_3

import com.android.hootr.testcicerone.fr_3.api.ApiFactory
import io.reactivex.Single
import java.util.*
import kotlin.random.Random

interface ICreatSeriesRepositories{
    fun getItems(uuidGood: String): List<Series>
    fun getSeries (uuidGood: String): Single<List<Series>>
}

class CreatSeriesRepositories: ICreatSeriesRepositories {
    override fun getItems(uuidGood: String): List<Series>
     = (0 until 15).map {
        when (Random.nextInt(15)) {
            0 -> S1
            1 -> S2
            2 -> S3
            3 -> S4
            4 -> S5
            5 -> S6
            6 -> S7
            7 -> S8
            8 -> S9
            9 -> S10
            10 -> S11
            11 -> S12
            12 -> S13
            13 -> S14
            else -> S15
        }.copy(name = "Серия ${it}")
    }

    override fun getSeries(uuidGood: String): Single<List<Series>> {
        return ApiFactory.apiService.getSeries(uuidGood)
    }


    companion object{

        private val S1 = Series("Серия 1", UUID.randomUUID().toString(), Calendar.getInstance().timeInMillis)
        private val S2 = Series("Серия 1", UUID.randomUUID().toString(), Calendar.getInstance().timeInMillis)
        private val S3 = Series("Серия 1", UUID.randomUUID().toString(), Calendar.getInstance().timeInMillis)
        private val S4 = Series("Серия 1", UUID.randomUUID().toString(), Calendar.getInstance().timeInMillis)
        private val S5 = Series("Серия 1", UUID.randomUUID().toString(), Calendar.getInstance().timeInMillis)
        private val S6 = Series("Серия 1", UUID.randomUUID().toString(), Calendar.getInstance().timeInMillis)
        private val S7 = Series("Серия 1", UUID.randomUUID().toString(), Calendar.getInstance().timeInMillis)
        private val S8 = Series("Серия 1", UUID.randomUUID().toString(), Calendar.getInstance().timeInMillis)
        private val S9 = Series("Серия 1", UUID.randomUUID().toString(), Calendar.getInstance().timeInMillis)
        private val S10 = Series("Серия 1", UUID.randomUUID().toString(), Calendar.getInstance().timeInMillis)
        private val S11 = Series("Серия 1", UUID.randomUUID().toString(), Calendar.getInstance().timeInMillis)
        private val S12 = Series("Серия 1", UUID.randomUUID().toString(), Calendar.getInstance().timeInMillis)
        private val S13 = Series("Серия 1", UUID.randomUUID().toString(), Calendar.getInstance().timeInMillis)
        private val S14 = Series("Серия 1", UUID.randomUUID().toString(), Calendar.getInstance().timeInMillis)
        private val S15 = Series("Серия 1", UUID.randomUUID().toString(), Calendar.getInstance().timeInMillis)
    }
}