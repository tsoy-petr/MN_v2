package com.android.hootr.navigationfragments

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.hootr.navigationfragments.type.HandleOnce
import com.android.hootr.navigationfragments.type.MyEvent
import io.reactivex.disposables.CompositeDisposable

class MyViewModel : ViewModel() {

    val currentBC: MutableLiveData<HandleOnce<String>> = MutableLiveData()

    val event: MutableLiveData<HandleOnce<MyEvent>> = MutableLiveData()

    val currentSeries: MutableLiveData<String> = MutableLiveData()


    val compositeDisposable = CompositeDisposable()


    fun getCurrentSereis(): LiveData<String> {
        return currentSeries
    }

    override fun onCleared() {

        compositeDisposable.dispose()
        super.onCleared()

    }

}