package com.android.hootr.mvvmretrofitdemo.service

import com.android.hootr.mvvmretrofitdemo.model.MovieApiResponse
import retrofit2.Call

import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApiService {
    @GET("movie/popular")
    fun getPopularMovies(@Query("api_key") apiKey: String?): Call<MovieApiResponse?>?
}