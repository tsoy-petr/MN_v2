package com.android.hootr.tsd.screens.event.login

sealed class EventLogin {

    data class ShowError(val messeg: String) : EventLogin()
    class Success() : EventLogin()
    class Loading(val isLoading: Boolean) : EventLogin()

}