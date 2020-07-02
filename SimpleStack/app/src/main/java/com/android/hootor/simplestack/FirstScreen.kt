package com.android.hootor.simplestack

import androidx.fragment.app.Fragment
import com.zhuinden.simplestackextensions.fragments.DefaultFragmentKey
import com.zhuinden.simplestackextensions.fragments.KeyedFragment
import kotlinx.android.parcel.Parcelize

@Parcelize
class FirstScreen: DefaultFragmentKey() {
    override fun instantiateFragment(): Fragment = FirstFragment()
}

