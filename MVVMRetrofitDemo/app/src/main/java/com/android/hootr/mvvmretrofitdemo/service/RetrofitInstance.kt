package com.android.hootr.mvvmretrofitdemo.service

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class RetrofitInstance {

    companion object {

        private val BASE_URL = "https://api.themoviedb.org/3/"
        private var movieApiService: MovieApiService? = null

        fun getService(): MovieApiService {

            movieApiService?.let {
                return it
            }

            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            val instance = retrofit.create(MovieApiService::class.java)
            movieApiService = instance

            return instance
        }
    }
}