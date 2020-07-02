package com.android.hootr.navigationfragments.ui


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.android.hootr.navigationfragments.R

/**
 * A simple [Fragment] subclass.
 */
class CreateSeriesFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_create_series, container, false)



        return view
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
