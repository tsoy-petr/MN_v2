package com.android.hootr.client1c.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

sealed class Screens: SupportAppScreen() {

    data class LoginFragmentScreen(val arrgs: Bundle?) : Screens() {
        override fun getScreenKey() = LoginFragment::class.java.simpleName
        override fun getFragment(): Fragment? = LoginFragment().apply {
                arguments = arrgs
            }
    }

    data class SettingsFragmentScreen(val arrgs: Bundle?) : Screens() {
        override fun getScreenKey() = SettingsFragment::class.java.simpleName
        override fun getFragment(): Fragment? = SettingsFragment().apply {
            arguments = arrgs
        }
    }

    data class MainMenuFragmentScreen(val arrgs: Bundle?) : Screens() {
        override fun getScreenKey() = MainMenuFragment::class.java.simpleName
        override fun getFragment(): Fragment? = MainMenuFragment().apply {
            arguments = arrgs
        }
    }

}