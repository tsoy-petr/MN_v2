package com.android.hootr.tsd.api.login

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class LoginApiService(val baseUrl: String) {

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .baseUrl(baseUrl)
        .build()

    val api = retrofit.create(LoginApi::class.java)

    fun loginUser(codeUser: String) = api.loginUser(codeUser)


}