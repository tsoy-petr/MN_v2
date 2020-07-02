package com.android.hootr.retrofitdemo.service

import com.android.hootr.retrofitdemo.model.CountryInfo
import retrofit2.Call
import retrofit2.http.GET

interface CountryService {

    @GET("country/get/all")
    fun getResults(): Call<CountryInfo>
}