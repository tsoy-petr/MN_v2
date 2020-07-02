package com.android.hootr.navigationfragments

import android.content.IntentFilter
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.android.hootr.navigationfragments.type.HandleOnce
import com.android.hootr.navigationfragments.type.MyEvent
import com.android.hootr.navigationfragments.ui.*
import com.android.hootr.tsd.servises.rxBroadcast.RxBroadcastReceivers

class MainActivity : AppCompatActivity() {

    private val TAG = "MainActivity"
    private lateinit var currentTAGFragment: String

//    private lateinit var navController: NavController

    private lateinit var viewModel: MyViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this).get(MyViewModel::class.java)

        viewModel.currentBC.observe(this, Observer { it ->
            it?.let { it ->
                val bc = it.getContentIfNotHandled()
                bc?.let {
                    Log.i(TAG, it)
                }

            }
        })

        viewModel.event.observe(this, Observer { it ->

            val currentEvent = it.getContentIfNotHandled()
            currentEvent?.let {
                when (it) {
                    is MyEvent.ShowQuantitentry -> showScanerfragmen()
                    is MyEvent.ShowMenu -> replace(MenuFragment())
                    is MyEvent.ShowSeriesSelection -> replace(SeriesListFragment())
                    is MyEvent.ShowSeriesCreation -> replace(CreateSeriesFragment())
                }
            }

        })

        viewModel.compositeDisposable.add(
            RxBroadcastReceivers.fromIntentFilter(
                applicationContext,
                IntentFilter("com.hootor.broadcast.BC_EVENT")
            ).subscribe {
                it?.let {
                    val bc: String? = it.getStringExtra("bc")

                    bc?.let {
                        viewModel.currentBC.value = HandleOnce(it)
                    }
                }
            }
        )

        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragment, ListFragment(), ListFragment::class.java.name)
                .commit()

            currentTAGFragment = ListFragment::class.java.name
        } else currentTAGFragment = savedInstanceState.getString("happy", ListFragment::class.java.name)

//        navController = Navigation.findNavController(this, R.id.fragment)
//        NavigationUI.setupActionBarWithNavController(this, navController)
    }

    private fun <T : Fragment> replace(fragment: T) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment, fragment, fragment::class.java.name)
            .addToBackStack(fragment::class.java.name)
            .commit()
    }

    private fun showScanerfragmen() {

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment, ScanerFragment(), ScanerFragment::class.java.name)
            .addToBackStack(ScanerFragment::class.java.name)
            .commit()

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        outState.putString("happy", currentTAGFragment)
    }

}
