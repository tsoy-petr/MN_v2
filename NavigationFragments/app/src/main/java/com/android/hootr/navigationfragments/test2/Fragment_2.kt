package com.android.hootr.navigationfragments.test2


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.android.hootr.navigationfragments.R
import com.android.hootr.navigationfragments.test1.state.ViewState
import kotlinx.android.synthetic.main.fragment_fragment_2.view.*

/**
 * A simple [Fragment] subclass.
 */
class Fragment_2 : Fragment() {

    lateinit var viewModel: TestViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_fragment_2, container, false)

        activity?.let {
            viewModel = ViewModelProvider(it).get(TestViewModel::class.java)
        }

        view.btnF2.setOnClickListener {
            viewModel.dataEvent(ViewState.Open_3())
        }

        return view
    }


}
