package com.android.hootr.testcicerone


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import ru.terrakok.cicerone.android.support.SupportAppNavigator


class MainActivity : AppCompatActivity() {

    private val navigator = SupportAppNavigator(this, R.id.fContainer)

    lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainViewModel =
            ViewModelProvider(this).get(MainViewModel::class.java)

        mainViewModel.mainActivityEvents.observe(this, Observer {

            val currentEvent = it.getContentIfNotHandled()
            currentEvent?.let {
                when(it) {
                    is MainActivityEvents.ShowFragment_1 -> App.instance.router().replaceScreen(Screens.FirstScreen)
                    is MainActivityEvents.ShowFragment_2 -> App.instance.router().navigateTo(Screens.SecondScreen())
                    is MainActivityEvents.ShowFragment_3 -> App.instance.router().navigateTo(Screens.ThreeScreen)
                    is MainActivityEvents.BackFragment_2 -> App.instance.router().backTo(Screens.SecondScreen())
                    is MainActivityEvents.BackFragment_1 -> App.instance.router().backTo(Screens.FirstScreen)
                    is MainActivityEvents.Exit -> App.instance.router().exit()
                }
            }

        })

        if(savedInstanceState == null) {
            mainViewModel.clickShowFragment(HandleOnce(MainActivityEvents.ShowFragment_1()))
        }



    }

    override fun onResume() {
        super.onResume()
        App.instance.navigatorHolder().setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        App.instance.navigatorHolder().removeNavigator()
    }

    override fun onBackPressed() {
        super.onBackPressed()
//        val rootFragment = supportFragmentManager.findFragmentById(R.id.fContainer)
//        if(rootFragment == null) {
//
//
////            super.onBackPressed()
//            App.instance.router().finishChain()
//        } else {
//            App.instance.router().exit()
//        }

    }

}
