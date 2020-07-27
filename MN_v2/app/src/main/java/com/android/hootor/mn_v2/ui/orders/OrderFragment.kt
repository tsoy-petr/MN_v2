package com.android.hootor.mn_v2.ui.orders

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.android.hootor.mn_v2.R
import com.android.hootor.mn_v2.ui.common.navigation.BackButtonListener
import com.android.hootor.mn_v2.ui.common.navigation.RouterProvider
import com.android.hootor.mn_v2.utils.injectViewModel
import com.android.hootor.mn_v2.utils.injectViewModelActivity
import com.android.hootor.mn_v2.utils.requireImpl
import javax.inject.Inject

class OrderFragment : Fragment(), BackButtonListener {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: SharedOrderViewModel

    private var uuidOrder = ""

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

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = injectViewModelActivity(factory = viewModelFactory)

        initArgs()
        initViewModel()
    }

    private fun initViewModel() {
        TODO("init viewmodel")
    }

    private fun initArgs(){
        uuidOrder = requireArguments().requireImpl("uuid")
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