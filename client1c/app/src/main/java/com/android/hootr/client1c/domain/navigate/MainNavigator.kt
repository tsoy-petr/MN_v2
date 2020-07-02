package com.android.hootr.client1c.domain.navigate

import android.os.Bundle

interface MainNavigator {
    fun showFragment(event: MainViewEvent, isRootFragment:Boolean)
}

sealed class MainViewEvent {

    data class ShowLogin(val args: Bundle? = Bundle.EMPTY): MainViewEvent()
    data class ShowSettings(val args: Bundle? = Bundle.EMPTY): MainViewEvent()
    data class ShowMainMenu(val args: Bundle? = Bundle.EMPTY): MainViewEvent()
    object None: MainViewEvent()
}