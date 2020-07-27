package com.android.hootor.quiz1c

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)

        bottomNavigationView.setupWithNavController(navHostFragment.findNavController())

        navHostFragment.findNavController()
            .addOnDestinationChangedListener { controller, destination, arguments ->
                when (destination.id) {
                    R.id.settingsFragment, R.id.listConfigurationFragment, R.id.statisticFragment,
                    R.id.syncFragment ->
                        bottomNavigationView.visibility = View.VISIBLE
                    else -> bottomNavigationView.visibility = View.GONE
                }

                when (destination.id) {
                    R.id.listConfigurationFragment -> {
                        tvToolbarTitle.text = "Тесты"
                        tvToolbarTitle.textAlignment = View.TEXT_ALIGNMENT_CENTER
                        appBarLayout.visibility = View.VISIBLE
//                        toolbar.visibility = View.VISIBLE
                    }
                    else -> {
                        appBarLayout.visibility = View.GONE
//                        toolbar.visibility = View.GONE
                    }
                }
            }

    }
}