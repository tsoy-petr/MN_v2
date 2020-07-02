package com.android.hootr.dogs.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.android.hootr.dogs.model.DogApiService
import com.android.hootr.dogs.model.DogBreed
import com.android.hootr.dogs.model.DogDatabase
import com.android.hootr.dogs.util.NotificationsHelper
import com.android.hootr.dogs.util.SharedPreferencesHelper
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch

class ListViewModel(application: Application) : BaseViewModel(application) {

    private var prefHelper = SharedPreferencesHelper(getApplication())

    private var refreshTime = 5 * 60 * 1000 * 1000 * 1000L

    private val dogsService = DogApiService()
    private val disposable = CompositeDisposable()

    val dogs = MutableLiveData<List<DogBreed>>()
    val dogsLoadError = MutableLiveData<Boolean>()
    val loading = MutableLiveData<Boolean>()

    fun refresh() {

        checkPrefDuration()

        val updateTime = prefHelper.getUpdateTime()

        if (updateTime != null && updateTime != 0L && System.nanoTime() - updateTime < refreshTime) {
            fetchFromDatabase()
        } else {
            fetchFromRemout()
        }

//        dogs.value = dogList
//        dogsLoadError.value = false
//        loading.value = false

    }

    private fun checkPrefDuration() {

        val cachePreference = prefHelper.getCacheDuration()

        try {
            val cachePreferenceInt = cachePreference?.toInt() ?: 5 * 60
            refreshTime = cachePreferenceInt.times(1000 * 1000 * 1000L)
        } catch (e: NumberFormatException){
            e.printStackTrace()
        }
    }

    fun refreshBypassCache() {
        fetchFromRemout()
    }

    private fun fetchFromDatabase() {

        loading.value = true

        launch {
            val dogs = DogDatabase.invoke(context = getApplication()).dogDao().gelAllDogs()
            dogsRetrieved(dogs)
        }
    }

    private fun fetchFromRemout() {

        loading.value = true

        dogsService.getDogs().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableSingleObserver<List<DogBreed>>() {
                override fun onSuccess(dogsList: List<DogBreed>) {

                    storeDogsLocally(dogsList)

                    NotificationsHelper(getApplication()).createNotification()

                }

                override fun onError(e: Throwable) {
                    dogsLoadError.value = true
                    loading.value = false
                    e.printStackTrace()
                }

            })
    }

    private fun dogsRetrieved(dogsList: List<DogBreed>) {
        dogs.value = dogsList
        dogsLoadError.value = false
        loading.value = false
    }

    private fun storeDogsLocally(list: List<DogBreed>) {
        launch {
            val dao = DogDatabase(getApplication()).dogDao()
            dao.deleteAllDogs()
            val resalt = dao.insertAll(*list.toTypedArray())
            var i = 0
            while (i < list.size) {
                list[i].uuid = resalt[i].toInt()
                i++
            }
            dogsRetrieved(list)
        }

        prefHelper.saveUpdateTime(System.nanoTime())
    }

    override fun onCleared() {

        disposable.clear()
        super.onCleared()
    }
}