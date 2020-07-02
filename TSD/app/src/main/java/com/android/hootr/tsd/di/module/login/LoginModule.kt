package com.android.hootr.tsd.di.module.login

import com.android.hootr.tsd.api.login.LoginApi
import com.android.hootr.tsd.di.module.ApiServiceModule
import com.android.hootr.tsd.screens.login.LoginRepositoryImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [ApiServiceModule::class])
class LoginModule {

    @Singleton
    @Provides
    fun getLoginRepository(loginApi: LoginApi) : LoginRepositoryImpl{
        return LoginRepositoryImpl(loginApi)
    }

}