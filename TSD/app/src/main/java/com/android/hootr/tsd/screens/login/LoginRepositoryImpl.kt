package com.android.hootr.tsd.screens.login

import com.android.hootr.tsd.api.login.LoginApi
import com.android.hootr.tsd.data.login.LoginData

class LoginRepositoryImpl(private val loginApi: LoginApi) : LoginRepository<LoginData> {

    override fun loginIn1C(userCode: String) = loginApi.loginUser(userCode)

}