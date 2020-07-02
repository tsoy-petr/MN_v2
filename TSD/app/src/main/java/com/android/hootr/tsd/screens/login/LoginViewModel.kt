package com.android.hootr.tsd.screens.login

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.android.hootr.tsd.screens.event.login.EventLogin

class LoginViewModel(application: Application): AndroidViewModel(application){

    val event = MutableLiveData<EventLogin>()


    override fun onCleared() {
        super.onCleared()
    }
}