package com.android.hootor.quiz1c.remote

import com.android.hootor.quiz1c.domain.QuizItem
import com.android.hootor.quiz1c.sync.SyncResponse
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {

    @GET("/api/quizList")
    fun getConfigurationsList(): Call<SyncResponse>

}