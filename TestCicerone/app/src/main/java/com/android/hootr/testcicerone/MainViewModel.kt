package com.android.hootr.testcicerone

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    val mainActivityEvents: MutableLiveData<HandleOnce<MainActivityEvents>> =
        MutableLiveData()

    fun clickShowFragment(events: HandleOnce<MainActivityEvents>) {
        mainActivityEvents.value = events
    }

    fun OnBack() {
        mainActivityEvents.value = HandleOnce(MainActivityEvents.Exit())
    }
}