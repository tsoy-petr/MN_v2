package com.android.hootr.mvvmretrofitdemo.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.android.hootr.mvvmretrofitdemo.model.MovieRepository
import com.android.hootr.mvvmretrofitdemo.model.Result

class MainActivityViewModel(application: Application) : AndroidViewModel(application) {

    val movieRepository: MovieRepository by lazy {
        MovieRepository(application)
    }

    fun getAllMovieData(): LiveData<List<Result>> {
        return movieRepository.getMutableLiveData()
    }
}