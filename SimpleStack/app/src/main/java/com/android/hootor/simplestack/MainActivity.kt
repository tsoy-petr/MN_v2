package com.android.hootor.simplestack

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.zhuinden.simplestack.History
import com.zhuinden.simplestack.SimpleStateChanger
import com.zhuinden.simplestack.StateChange
import com.zhuinden.simplestack.navigator.Navigator
import com.zhuinden.simplestackextensions.fragments.DefaultFragmentStateChanger
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), SimpleStateChanger.NavigationHandler {

    private lateinit var fragmentStateChanger: DefaultFragmentStateChanger


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fragmentStateChanger = DefaultFragmentStateChanger(supportFragmentManager, R.id.root)

        Navigator.configure()
            .setStateChanger(SimpleStateChanger(this))
            .install(this, root, History.of(FirstScreen()))

    }

    override fun onBackPressed() {
        if (!Navigator.onBackPressed(this)) {
            super.onBackPressed()
        }
    }

    override fun onNavigationEvent(stateChange: StateChange) {
        fragmentStateChanger.handleStateChange(stateChange)
    }

}