package com.android.hootr.tsd.screens.testFragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import com.android.hootr.tsd.R
import kotlinx.android.synthetic.main.fragment_scaning_barcode.*

class ScaningFragment : Fragment() {

    companion object {
        fun newInstance() = ScaningFragment()
    }

    private lateinit var viewModel: NewArriveViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_scaning_barcode, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val owner: ViewModelStoreOwner = activity ?: this
        viewModel = ViewModelProvider(owner).get(NewArriveViewModel::class.java)

        btn_exit_scaning_barcode_goods.setOnClickListener{
            viewModel.setEvent(VMEventViewNewArrive.MenyNewArrive())
        }
    }

}
