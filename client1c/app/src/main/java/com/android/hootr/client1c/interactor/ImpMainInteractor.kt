package com.android.hootr.client1c.interactor

import com.android.hootr.client1c.prefs.SharedPrefsManager
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class ImpMainInteractor @Inject constructor(
    val prefs: SharedPrefsManager
) : MainInteractor {

    fun checkSetiings(): Boolean  {
        val urlServer = prefs.getURLServer()
        return if (urlServer == null || urlServer.isEmpty()) false else true
    }

    fun isLogin(): Boolean = prefs.isLogin()



    fun getChackSettingsLigins(): Single<CheckResult> {

        val res = CheckResult(checkSetiings(), isLogin())

        return Single.create<CheckResult> {
            it.onSuccess(res)
        }
            .observeOn(Schedulers.io())
            .delay(5, TimeUnit.SECONDS)

    }

    data class CheckResult(
        val isSetting: Boolean,
        val isLogin: Boolean
    )
}