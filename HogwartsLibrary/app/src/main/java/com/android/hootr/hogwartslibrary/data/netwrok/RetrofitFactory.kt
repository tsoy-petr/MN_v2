package com.android.hootr.hogwartslibrary.data.netwrok

import com.android.hootr.hogwartslibrary.data.services.CharactersService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit

class RetrofitFactory {

    //    $2a$10$do/WarfkYkvhcb3vaJFiBO5qM2NAZvqx3zDgkvAPPvLR33cQoqDCO
    companion object {
        val key = "\$2a\$10\$do/WarfkYkvhcb3vaJFiBO5qM2NAZvqx3zDgkvAPPvLR33cQoqDCO"
        val instance = RetrofitFactory()
    }

    private fun okHttpInterceptor(): HttpLoggingInterceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return httpLoggingInterceptor
    }

    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(okHttpInterceptor())
        .build()

    private val retrofitClient: Retrofit = Retrofit.Builder()
        .baseUrl("https://www.potterapi.com/v1/")
        .client(okHttpClient)
        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
        .build()

    val charactersService = retrofitClient.create(CharactersService::class.java)
}