package com.android.hootr.tsd.screens.testFragments

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class NewArriveViewModel : ViewModel() {

    private val event = MutableLiveData<VMEventViewNewArrive>()

    fun setEvent(vmEvent: VMEventViewNewArrive) {
        event.value = vmEvent
    }

    fun getEvent(): LiveData<VMEventViewNewArrive> = event

}
