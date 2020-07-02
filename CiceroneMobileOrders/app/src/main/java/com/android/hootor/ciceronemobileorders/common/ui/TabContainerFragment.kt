package com.android.hootor.ciceronemobileorders.common.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.android.hootor.ciceronemobileorders.R
import com.android.hootor.ciceronemobileorders.ui.Screens
import com.android.hootor.ciceronemobileorders.common.BackButtonListener
import com.android.hootor.ciceronemobileorders.common.RouterProvider
import com.android.hootor.ciceronemobileorders.common.subnavigation.LocalCiceroneHolder
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.Router
import ru.terrakok.cicerone.Screen
import ru.terrakok.cicerone.android.support.SupportAppNavigator

open class TabContainerFragment: Fragment(), RouterProvider, BackButtonListener {

    open val layuot =
        R.layout.fragment_tab_container
    open val ftcContainer =
        R.id.ftc_container

    private val navigator: Navigator by lazy {
            SupportAppNavigator(activity!!, childFragmentManager, ftcContainer)
    }

    val ciceroneHolder = LocalCiceroneHolder()

    companion object {

        open val EXTRA_NAME = "tcf_extra_name"

        fun getNewInstance(name: String): TabContainerFragment {
            val fragment =
                TabContainerFragment()
            val arguments = Bundle()
            arguments.putString(EXTRA_NAME, name)
            fragment.arguments = arguments
            return fragment
        }
    }

    fun getContainerName() = arguments?.getString(EXTRA_NAME)

    fun getCicerone() = ciceroneHolder.getCicerone(getContainerName())

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(layuot, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (childFragmentManager.findFragmentById(ftcContainer) == null) {
            getCicerone().router.replaceScreen(getDefaultForwardScreen())
        }
    }

    open fun getDefaultForwardScreen(): Screen {
        return Screens.ForwardScreen(
            getContainerName(),
            0
        )
    }

    override fun onResume() {
        super.onResume()
        getCicerone().navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        getCicerone().navigatorHolder.removeNavigator()
        super.onPause()
    }
    override fun getRouter(): Router = getCicerone().router


    override fun onBackPressed(): Boolean {
        val fragment =
            childFragmentManager.findFragmentById(ftcContainer)
        return if (fragment != null && fragment is BackButtonListener
            && (fragment as BackButtonListener).onBackPressed()
        ) {
            true
        } else {
            (activity as RouterProvider?)!!.router.exit()
            true
        }
    }
}