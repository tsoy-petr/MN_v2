package com.android.hootor.mn_v2.utils

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

inline fun <reified T : ViewModel> FragmentActivity.injectViewModel(factory: ViewModelProvider.Factory): T {
    return ViewModelProvider(this, factory).get(T::class.java)
}

inline fun <reified T : ViewModel> Fragment.injectViewModel(factory: ViewModelProvider.Factory): T {
    return ViewModelProvider(this, factory).get(T::class.java)
}

inline fun <reified T : ViewModel> Fragment.injectViewModelActivity(factory: ViewModelProvider.Factory): T {
    return ViewModelProvider(requireActivity(), factory).get(T::class.java)
}

inline fun <reified T : ViewModel> Fragment.injectViewModelParentFragment(factory: ViewModelProvider.Factory): T {
    return parentFragment?.let { ViewModelProvider(it, factory).get(T::class.java)  }
    ?: throw IllegalStateException("Wrong parent fragment")
}