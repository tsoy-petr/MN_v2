package com.android.hootr.transportapp.screens.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.android.hootr.presentation.screens.countries.CountriesFragment
import com.android.hootr.transportapp.R
import dagger.android.AndroidInjection

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
            .replace(R.id.flMainActivity, CountriesFragment.newInstance())
            .commitNow()
    }
}
