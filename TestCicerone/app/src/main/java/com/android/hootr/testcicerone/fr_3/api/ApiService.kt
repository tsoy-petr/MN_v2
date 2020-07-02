package com.android.hootr.testcicerone.fr_3.api

import com.android.hootr.testcicerone.fr_3.Series
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("tsoy-petr/testCoints/data")
    fun getSeries(
        @Query(QUERY_PARAM_UUID_GOOD)
        uuid: String
    ): Single<List<Series>>

    @GET("tsoy-petr/testCoints/data")
    suspend fun getSeriesCourotines(
        @Query(QUERY_PARAM_UUID_GOOD)
        uuid: String
    ): List<Series>

    companion object {
        private const val QUERY_PARAM_UUID_GOOD = "uuidGood"
    }

}