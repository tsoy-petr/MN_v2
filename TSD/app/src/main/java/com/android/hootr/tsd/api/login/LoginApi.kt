package com.android.hootr.tsd.api.login

import com.android.hootr.tsd.data.login.LoginData
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface LoginApi {

    @GET("/login")
    fun loginUser(
        @Query(QUERY_PARAM_CODE_USER) codeUser: String
    ):Single<LoginData>

    companion object{
        private const val QUERY_PARAM_CODE_USER = "codeUser"
    }
}