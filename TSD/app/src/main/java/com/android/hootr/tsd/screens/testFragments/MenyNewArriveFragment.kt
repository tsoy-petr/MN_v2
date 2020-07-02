package com.android.hootr.tsd.screens.testFragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import com.android.hootr.tsd.R
import kotlinx.android.synthetic.main.blank_fragment1_fragment.*

class MenyNewArriveFragment : Fragment() {

    companion object {
        fun newInstance() = MenyNewArriveFragment()
    }

    private lateinit var viewModel: NewArriveViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.blank_fragment1_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val owner: ViewModelStoreOwner = activity ?: this
        viewModel = ViewModelProvider(owner).get(NewArriveViewModel::class.java)

        btn_start_scaning.setOnClickListener {
            viewModel.setEvent(VMEventViewNewArrive.StartScaning())
        }

        btn_goods.setOnClickListener {
            viewModel.setEvent(VMEventViewNewArrive.Goods())
        }

        btn_ctemporarily_exit.setOnClickListener {
            viewModel.setEvent(VMEventViewNewArrive.CtemporarilyExit())
        }

        btn_complete.setOnClickListener {
            viewModel.setEvent(VMEventViewNewArrive.Complete())
        }


    }

}
