package com.android.hootr.hogwartslibrary.data.services

import com.android.hootr.hogwartslibrary.data.models.CharacterRemote
import retrofit2.http.GET
import retrofit2.http.Query

interface CharactersService {

    @GET("./characters")
    suspend fun getAllCharacters(@Query("key") key: String): List<CharacterRemote>
}