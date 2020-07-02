package com.android.hootr.navigationfragments.test1


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.android.hootr.navigationfragments.R
import kotlinx.android.synthetic.main.fragment_b.*

/**
 * A simple [Fragment] subclass.
 */
class BFragment : Fragment() {

    lateinit var callback: ClickListner

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_b, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        try {

            callback = parentFragment as ClickListner

        } catch (e: ClassCastException) {
            throw ClassCastException("Calling fragment must implement Callback interface")
        }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        buttonOpenFragmentC.setOnClickListener {
            callback?.let {
                it.onClick()
            }
        }

    }


    interface ClickListner {
        fun onClick()
    }

}
