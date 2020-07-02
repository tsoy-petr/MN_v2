package com.android.hootr.navigationfragments.test1

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

class FragmentUtil {

    companion object {

        fun getFragmentByTagName(
            fragmentManager: FragmentManager,
            fragmentTagName: String
        ): Fragment? {

            var ret: Fragment? = null

            val fragmentList = fragmentManager.fragments

            if (fragmentList.size > 0) {

                val size = fragmentList.size

                for (i in 0..size-1) {

                    val fragment = fragmentList.get(i)

                    fragment?.let {
                        val fragmentTag = it.tag
                        if (fragmentTag === fragmentTagName) {
                            return fragment
                        }
                    }

                }

            }


            return ret
        }

    }
}