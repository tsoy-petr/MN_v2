package com.android.hootr.tsd.screens

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.android.hootr.tsd.R
import com.android.hootr.tsd.screens.testFragments.*

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: NewArriveViewModel

    companion object {

        fun startActivity(context: Context) {
            val starter = Intent(context, MainActivity::class.java)
            starter.flags = (Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
            context.startActivity(starter)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this).get(NewArriveViewModel::class.java)
        viewModel.getEvent().observe(this, Observer {

            Log.i("happy", it.toString())

                        when (it) {
                            is VMEventViewNewArrive.MenyNewArrive -> showOperation(MenyNewArriveFragment.newInstance())
                            is VMEventViewNewArrive.StartScaning -> showOperation(ScaningFragment.newInstance())
                            is VMEventViewNewArrive.Goods -> showOperation(GoodsFragment.newInstance())
                            is VMEventViewNewArrive.CtemporarilyExit -> finish()
                            is VMEventViewNewArrive.Complete -> finish()
                        }
        })

        showOperation(MenyNewArriveFragment())
    }

    private fun showOperation(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.box, fragment)
            .commit()
    }

    override fun onBackPressed() {

        if(supportFragmentManager.backStackEntryCount == 0 ){
            showOperation(MenyNewArriveFragment())
        } else {
            super.onBackPressed()
        }

    }
}
