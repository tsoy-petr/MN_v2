package com.android.hootor.ciceronemyapp.screens

import androidx.fragment.app.Fragment
import com.android.hootor.ciceronemyapp.screens.root.RootFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

sealed class Screens {

    object RootScreen: SupportAppScreen() {
        override fun getFragment(): Fragment? {

            return RootFragment()

        }
    }

    object F1Screen: SupportAppScreen() {
        override fun getFragment(): Fragment? {

            return Fragment1()

        }
    }

    object F2Screen: SupportAppScreen() {
        override fun getFragment(): Fragment? {

            return Fragment2()

        }
    }

}