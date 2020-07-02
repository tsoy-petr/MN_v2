package com.android.hootr.navigationfragments.test2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.android.hootr.navigationfragments.R
import com.android.hootr.navigationfragments.test1.state.DataState

class Main2Activity : AppCompatActivity() {

    lateinit var viewModel: TestViewModel

    companion object {
        val BACK_STACK_ROOT_TAG = "root_fragment"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        if (savedInstanceState == null) {
            onTabSelected(1)
        }

        viewModel = ViewModelProvider(this).get(TestViewModel::class.java)
        viewModel?.let {
            it.dataStatePub.observe(this, Observer { handleViewStat ->
                handleViewStat.getContentIfNotHandled()?.let {dataState ->
                    when (dataState) {

                        is DataState.RepF1 -> addFragmentOnTop(Fragment_1())
                        is DataState.RepF2 -> addFragmentOnTop(Fragment_2())
                        is DataState.RepF3 -> addFragmentOnTop(Fragment_3())
                        is DataState.RepF4 -> addFragmentOnTop(Fragment_4())
                    }
                }


            })
        }
    }

    fun onTabSelected(position: Int) {

        // Pop off everything up to and including the current tab
        supportFragmentManager.popBackStack(
            BACK_STACK_ROOT_TAG,
            FragmentManager.POP_BACK_STACK_INCLUSIVE
        )

        // Add the new tab fragment
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, Fragment_1())
            .addToBackStack(BACK_STACK_ROOT_TAG)
            .commit()
    }

    fun addFragmentOnTop(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.container, fragment)
            .addToBackStack(null)
            .commit()
    }

    override fun onBackPressed() {

        if (supportFragmentManager.backStackEntryCount > 1) {
            supportFragmentManager.popBackStackImmediate()
        } else {
            finish()
        }
    }
}
