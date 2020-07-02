package com.android.hootr.hogwartslibrary.data.services

import com.android.hootr.hogwartslibrary.data.models.CharacterRemote
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface HousesService {

    companion object {
        val ryffindorId = "5a05e2b252f721a3cf2ea33f"
        val ravenclawID = "5a05da69d45bd0a11bd5e06f"
        val slytherinId = "5a05dc8cd45bd0a11bd5e071"
        val hufflepuffId = "5a05dc58d45bd0a11bd5e070"
    }

    @GET("./houses")
    suspend fun getAllCharacters(@Query("key") key: String, @Path("houseId") houseId: String): List<HouseRemote>
}
}