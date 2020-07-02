package com.android.hootr.tsd.screens.login

import io.reactivex.Single

interface LoginRepository<P> {

    fun loginIn1C(userCode: String): Single<P>

}