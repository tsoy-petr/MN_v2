package com.android.hootor.simplestack

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.zhuinden.simplestackextensions.fragments.KeyedFragment

class FirstFragment : KeyedFragment(R.layout.fragment_first) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val key: FirstScreen = getKey() // params
    }

}