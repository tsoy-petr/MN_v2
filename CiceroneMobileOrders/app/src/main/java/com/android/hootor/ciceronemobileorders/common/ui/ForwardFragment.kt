package com.android.hootor.ciceronemobileorders.common.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.android.hootor.ciceronemobileorders.R
import com.android.hootor.ciceronemobileorders.common.BackButtonListener
import com.android.hootor.ciceronemobileorders.common.RouterProvider
import ru.terrakok.cicerone.Router

open class ForwardFragment : Fragment(), BackButtonListener {

    open val layout = R.layout.fragment_forward
    open var toolbar: Toolbar? = null
    private lateinit var router: Router


    companion object {

        val EXTRA_NAME = "extra_name"
        val EXTRA_NUMBER = "extra_number"

        open fun getNewInstance(name: String?, number: Int?): ForwardFragment {
            val fragment =
                ForwardFragment()
            val arguments = Bundle()
            arguments.putString(EXTRA_NAME, name)
            arguments.putInt(EXTRA_NUMBER, number?:0)
            fragment.arguments = arguments
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        router = (parentFragment as RouterProvider).router
        toolbar = view.findViewById(R.id.toolbar)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        toolbar?.apply {
            title = arguments?.getString(EXTRA_NAME)
            setNavigationOnClickListener {
                router.exit()
            }
        }
//        toolbar.title = arguments?.getString(EXTRA_NAME)
//        toolbar.setNavigationOnClickListener {
//            router.exit()
//        }
//        forwardBt.setOnClickListener {
//            router.navigateTo(Screens.ForwardScreen(arguments?.getString(EXTRA_NAME), number + 1))
//        }
//        githubBt.setOnClickListener {
//            //TODO
//        }
//
//        var chain = "[0]"
//        for (i in 0 until number) {
//            chain += "➔" + (i + 1)
//        }
//        Log.i("happy", "chain - $chain")
//        chainTV.text = chain

    }

    override fun onBackPressed(): Boolean {
        router.exit()
        return true
    }
}