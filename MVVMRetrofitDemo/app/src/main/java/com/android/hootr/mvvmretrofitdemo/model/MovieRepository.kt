package com.android.hootr.mvvmretrofitdemo.model

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.android.hootr.mvvmretrofitdemo.service.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieRepository(val application: Application) {

    private val mutableLiveData: MutableLiveData<List<Result>> = MutableLiveData()

    fun getMutableLiveData(): MutableLiveData<List<Result>> {
        val movieApiService = RetrofitInstance.getService()
        val call = movieApiService.getPopularMovies("e9d6b2db34845e6100bc75f8e904ebc5")

        call?.let {
            it.enqueue(object : Callback<MovieApiResponse?> {

                override fun onFailure(call: Call<MovieApiResponse?>, t: Throwable) {

                }

                override fun onResponse(
                    call: Call<MovieApiResponse?>,
                    response: Response<MovieApiResponse?>
                ) {

                    val movieApiResponse = response.body()

                    movieApiResponse?.let { mar ->
                        mar.results.let {
                            mutableLiveData.value = it
                        }
                    }

                }
            })
        }

        return mutableLiveData
    }
}