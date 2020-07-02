package com.android.hootr.navigationfragments.test2

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.hootr.navigationfragments.test1.state.DataState
import com.android.hootr.navigationfragments.test1.state.ViewState
import com.android.hootr.navigationfragments.type.HandleOnce

class TestViewModel(): ViewModel() {

    val dataState: MutableLiveData<HandleOnce<DataState>> = MutableLiveData()

    val dataStatePub: LiveData<HandleOnce<DataState>>
        get() = dataState

    fun dataEvent(viewState: ViewState) {
        when(viewState) {
            is ViewState.Open_1 -> dataState.value = HandleOnce(DataState.RepF1())
            is ViewState.Open_2 -> dataState.value = HandleOnce(DataState.RepF2())
            is ViewState.Open_3 -> dataState.value = HandleOnce(DataState.RepF3())
            is ViewState.Open_4 -> dataState.value = HandleOnce(DataState.RepF4())
        }
    }

}