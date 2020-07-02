package com.android.hootor.mn_v2.ui.orders

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.android.hootor.mn_v2.R
import com.android.hootor.mn_v2.ui.common.navigation.BackButtonListener
import com.android.hootor.mn_v2.ui.common.navigation.RouterProvider

class OrderFragment : Fragment(), BackButtonListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_order, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance(args: Bundle? = null) =
            OrderFragment().apply {
                arguments = args
            }
    }

    override fun onBackPressed(): Boolean {
        activity?.let {
            (it as RouterProvider).router.exit()
        }
        return true
    }
}