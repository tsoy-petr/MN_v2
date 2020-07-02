package com.android.hootr.client1c

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.android.hootr.client1c.domain.navigate.MainNavigator
import com.android.hootr.client1c.domain.navigate.MainViewEvent
import com.android.hootr.client1c.prefs.SharedPrefsManager
import com.android.hootr.client1c.presentation.MainViewPresentation
import com.android.hootr.client1c.ui.Screens
import kotlinx.android.synthetic.main.toolbar.*
import ru.terrakok.cicerone.android.support.SupportAppNavigator
import javax.inject.Inject

class MainActivity : AppCompatActivity(), MainNavigator {

    private val navigator = SupportAppNavigator(this, R.id.fragmentContainer)

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var prefs: SharedPrefsManager

    lateinit var viewModel: MainViewPresentation

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        App.appComponent.inject(this)

        viewModel = ViewModelProvider(this, viewModelFactory)[MainViewPresentation::class.java]
        viewModel.getViewEventLivedata().observe(this, Observer {

            it?.message?.getContentIfNotHandled()?.let{
                Toast.makeText(this, it, Toast.LENGTH_LONG).show()
            }

            val navEvent = it?.viewState?.getContentIfNotHandled()
            navEvent?.let {
                when (it) {
                    is MainViewEvent.ShowSettings -> App.instance.router().newRootScreen(
                        Screens.SettingsFragmentScreen(
                            Bundle.EMPTY
                        )
                    )
                    is MainViewEvent.ShowLogin -> App.instance.router().newRootScreen(
                        Screens.LoginFragmentScreen(
                            Bundle.EMPTY
                        )
                    )
                    is MainViewEvent.ShowMainMenu -> App.instance.router().newRootScreen(
                        Screens.MainMenuFragmentScreen(
                            Bundle.EMPTY
                        )
                    )
                }
            }

            if (it.isProgress) {
                showLoading()
            } else hideProgress()
        })

//        if (savedInstanceState == null) {
//            viewModel.setScreen(MainViewEvent.ShowMainMenu())
//        }
//        if (savedInstanceState == null) {
//            viewModel.loginSettings()
//        }

    }

    private fun showLoading() {
        toolbar_progress_bar.visibility = View.VISIBLE
    }

    private fun hideProgress() {
        toolbar_progress_bar.visibility = View.GONE
    }

    override fun showFragment(event: MainViewEvent, isRootFragment: Boolean) {


    }

    override fun onResume() {
        super.onResume()
        App.instance.navigatorHolder().setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        App.instance.navigatorHolder().removeNavigator()
    }
}
