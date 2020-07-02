package com.android.hootr.testcicerone

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.fragment_2.*

/**
 * A simple [Fragment] subclass.
 */
class Fragment_2 : Fragment() {

    lateinit var mainViewModel: MainViewModel
    lateinit var localViewModel: Fragment_2PresentationModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_2, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        activity?.let {
            mainViewModel = ViewModelProvider(it).get(MainViewModel::class.java)
        }

        localViewModel = ViewModelProvider(this)[Fragment_2PresentationModel::class.java]
        localViewModel.getSeriesLiveData().observe(this, Observer {model ->
            tvUuidNewSeries.text = model.uuidSeries
        })

        btnClickOpenThreefr.setOnClickListener {
            mainViewModel.clickShowFragment(HandleOnce(MainActivityEvents.ShowFragment_3()))
        }


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun onPause() {
        super.onPause()

    }

    override fun onDestroy() {

        super.onDestroy()
    }


}
