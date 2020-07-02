package com.android.hootr.tsd.data.login

data class LoginData(
    val isLogin: Boolean,
    val errorMessage: String,
    val codeUser: String, val nodeCode: String
)