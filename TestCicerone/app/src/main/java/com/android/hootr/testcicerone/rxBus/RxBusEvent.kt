package com.android.hootr.testcicerone.rxBus

sealed class RxBusEvent {

    data class SelectedSeriesEvent(val uuidSeries: String)

}