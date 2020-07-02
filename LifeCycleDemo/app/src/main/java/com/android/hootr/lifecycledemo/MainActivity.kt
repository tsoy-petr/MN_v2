package com.android.hootr.lifecycledemo

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.android.hootr.lifecycledemo.ui.main.MainFragment
import kotlinx.android.synthetic.main.main_activity.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment.newInstance())
                .commitNow()
        }

        lifecycleScope.launch {
            delay(5000)
            progressBar.visibility = View.VISIBLE
            delay(10_000)
            progressBar.visibility = View.GONE
        }
    }

}
