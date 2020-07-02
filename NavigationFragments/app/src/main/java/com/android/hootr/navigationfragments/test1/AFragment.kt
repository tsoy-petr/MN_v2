package com.android.hootr.navigationfragments.test1


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
class AFragment : Fragment(), BFragment.ClickListner {

    var currentFragment: String = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_a, container, false)

        var fragment = FragmentUtil.getFragmentByTagName(
            childFragmentManager,
            BFragment::class.java.simpleName
        )
        if (fragment == null) {
            fragment = BFragment()
        }
        childFragmentManager.beginTransaction()
            .replace(
                R.id.fragment_container_view_tag,
                BFragment(),
                BFragment::class.java.simpleName
            )
            .addToBackStack(null)
            .commit()

        Log.i("happy", "onCreateView: AFragment")

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.i("happy", "onViewCreated: AFragment")

    }

    override fun onSaveInstanceState(outState: Bundle) {
        Log.i("happy", "onSaveInstanceState: AFragment")
        super.onSaveInstanceState(outState)
        outState.putString("currentFragment", currentFragment)
    }

    override fun onDestroy() {
        Log.i("happy", "onDestroy: AFragment")
        super.onDestroy()
    }

    override fun onClick() {

        val transaction = childFragmentManager.beginTransaction()
        var bFragment = FragmentUtil.getFragmentByTagName(
            childFragmentManager,
            BFragment::class.java.simpleName
        )
        if (bFragment != null) {
            transaction.hide(bFragment)
        }

        transaction
            .add(
                R.id.fragment_container_view_tag,
                CFragment(),
                CFragment::class.java.simpleName
            )
            .addToBackStack(null)
            .commit()
    }


}
