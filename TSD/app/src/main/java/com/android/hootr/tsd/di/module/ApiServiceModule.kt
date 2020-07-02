package com.android.hootr.tsd.di.module

import com.android.hootr.tsd.api.login.LoginApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module(includes = [RetrofitModule::class])
class ApiServiceModule {

    @Singleton
    @Provides
    fun getLoginApi(retorofir: Retrofit): LoginApi {
        return retorofir.create(LoginApi::class.java)
    }

}