package com.android.hootr.navigationfragments.ui


import android.content.Intent
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
import kotlinx.android.synthetic.main.fragment_list.view.*
import java.util.*

/**
 * A simple [Fragment] subclass.
 */
class ListFragment : Fragment() {

    lateinit var viewModel: MyViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_list, container, false)

        activity?.let {
            viewModel = ViewModelProvider(it).get(MyViewModel::class.java)
        }

//        viewModel.currentBC.observe(this, androidx.lifecycle.Observer {
//
//            it?.let {
//                val bc = it.getContentIfNotHandled()
//                bc?.let {
//                    Log.i("ListFragment", it)
//                }
//
//            }
//
//        })

        view.buttonListFragmentBC.setOnClickListener {
            val intent = Intent()
            intent.setAction("com.hootor.broadcast.BC_EVENT")
            intent.putExtra("bc", UUID.randomUUID().toString())
            activity?.sendBroadcast(intent)
        }

        view.btnOpenScaning.setOnClickListener {
            viewModel.event.value = HandleOnce(MyEvent.ShowQuantitentry())
        }

//        view.btnStartMenu.setOnClickListener {
//            findNavController().navigate(R.id.action_listFragment_to_menuFragment)
//        }

        Log.i("happy", "ListFragment - onCreateView")

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
