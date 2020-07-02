package com.android.hootr.rxbusexample

class RxBusEvent {

    data class ProgressEvent(
        val showDialog: Boolean,
        val message: String? = null
    )

    data class LogOut(val logout: Boolean)

    data class MessageEvent(val message: String)


}