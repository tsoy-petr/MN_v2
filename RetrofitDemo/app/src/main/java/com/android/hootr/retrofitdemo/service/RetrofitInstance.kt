package com.android.hootr.retrofitdemo.service

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInstance private constructor() {

    companion object {

        val BASE_URL = "http://groupkt.com/"

        private var service: CountryService? = null

        fun getService(): CountryService {

            service?.let {
                return it
            }

            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            val instance = retrofit.create(CountryService::class.java)
            service = instance

            return instance

        }


    }

}