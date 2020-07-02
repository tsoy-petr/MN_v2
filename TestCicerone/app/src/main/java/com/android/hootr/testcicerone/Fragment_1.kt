package com.android.hootr.testcicerone

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.fragment_1.*

/**
 * A simple [Fragment] subclass.
 */
class Fragment_1 : Fragment() {

     lateinit var mainViewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_1, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        activity?.let {
            mainViewModel = ViewModelProvider(it).get(MainViewModel::class.java)
        }

        btnClickOpenSeconfr.setOnClickListener {
            mainViewModel.clickShowFragment(HandleOnce(MainActivityEvents.ShowFragment_2()))
        }

    }

}
