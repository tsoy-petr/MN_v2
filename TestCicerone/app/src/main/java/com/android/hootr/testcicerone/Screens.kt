package com.android.hootr.testcicerone

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.android.hootr.testcicerone.fr_3.Fragment_3
import ru.terrakok.cicerone.android.support.FragmentParams
import ru.terrakok.cicerone.android.support.SupportAppScreen

sealed class Screens {

    object FirstScreen : SupportAppScreen() {

        override fun getScreenKey() = Fragment_1::class.java.simpleName

        override fun getFragment(): Fragment? {
            return Fragment_1()
        }
    }

    class SecondScreen(var uuidNewSeries: String? = null) : SupportAppScreen() {

        override fun getScreenKey() = Fragment_2::class.java.simpleName

        override fun getFragment(): Fragment? {
            return Fragment_2()
        }

        override fun getFragmentParams(): FragmentParams? {

            uuidNewSeries?.let {
                val args = Bundle()
                args.putString(UUIDNEWSERIES_KEY, uuidNewSeries)
                return FragmentParams(
                    Fragment_2::class.java,
                    args
                )
            }
            return super.getFragmentParams()
        }

        companion object {
            val UUIDNEWSERIES_KEY = "uuidNewSeries"
        }
    }

    object ThreeScreen : SupportAppScreen() {

        override fun getScreenKey() = Fragment_3::class.java.simpleName


        override fun getFragment(): Fragment? {
            return Fragment_3()
        }
    }

}

