package com.android.hootor.quiz1c.sync

import android.util.Log
import com.android.hootor.quiz1c.domain.QuizItem
import com.android.hootor.quiz1c.remote.ApiService
import com.android.hootor.quiz1c.remote.core.Either
import com.android.hootor.quiz1c.remote.core.Failure
import com.android.hootor.quiz1c.remote.core.Request
import retrofit2.Call

class SyncInteractor(
    private val api: ApiService,
    private val request: Request
){

    suspend fun getConfigurations(): Either<Failure, List<QuizItem.Quiz>> {

        var res: Call<SyncResponse>? = null
        try {
            res = api.getConfigurationsList()
            return request.make(res) {
                it.data.toModelQuizItem()
            }
        } catch (e: Exception) {

            return Either.Left<Failure>(Failure.NetworkConnectionError)
        }
    }

}