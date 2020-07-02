package com.android.hootr.navigationfragments.ui


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.android.hootr.navigationfragments.MyViewModel
import com.android.hootr.navigationfragments.R
import com.android.hootr.navigationfragments.type.HandleOnce
import com.android.hootr.navigationfragments.type.MyEvent
import kotlinx.android.synthetic.main.fragment_scaner.view.*

/**
 * A simple [Fragment] subclass.
 */
class ScanerFragment : Fragment() {

    lateinit var viewModel: MyViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_scaner, container, false)

        activity?.let {
            viewModel = ViewModelProvider(it).get(MyViewModel::class.java)
        }

        view.btnShowSeriesList.setOnClickListener {
            viewModel.event.value = HandleOnce(MyEvent.ShowSeriesSelection())
        }

        return  view
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i("happy", "${this::class.java.simpleName} - onCreate")
    }

    override fun onPause() {
        super.onPause()
        Log.i("happy", "${this::class.java.simpleName} - onPause")
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Log.i("happy", "${this::class.java.simpleName} - onActivityCreated")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("happy", "${this::class.java.simpleName} - onDestroy")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.i("happy", "${this::class.java.simpleName} - onDestroyView")
    }

    override fun onResume() {
        super.onResume()
        Log.i("happy", "${this::class.java.simpleName} - onResume")
    }

    override fun onStart() {
        super.onStart()
        Log.i("happy", "${this::class.java.simpleName} - onStart")
    }

    override fun onStop() {
        super.onStop()
        Log.i("happy", "${this::class.java.simpleName} - onStop")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.i("happy", "${this::class.java.simpleName} - onSaveInstanceState")
    }
}
