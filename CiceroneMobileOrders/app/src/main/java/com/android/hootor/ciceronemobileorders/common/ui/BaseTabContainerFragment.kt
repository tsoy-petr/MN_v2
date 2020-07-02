package com.android.hootor.ciceronemobileorders.common.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.android.hootor.ciceronemobileorders.common.BackButtonListener
import com.android.hootor.ciceronemobileorders.common.RouterProvider
import com.android.hootor.ciceronemobileorders.common.subnavigation.LocalCiceroneHolder
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.Router
import ru.terrakok.cicerone.android.support.SupportAppNavigator

abstract class BaseTabContainerFragment: Fragment(), RouterProvider, BackButtonListener {

    abstract var ftcContainer: Int?

    private val navigator: Navigator? by lazy {
        initNavigator()
    }

    val ciceroneHolder = LocalCiceroneHolder()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        intiDefaultScreen()
    }

    abstract fun initNavigator() : SupportAppNavigator?

    fun getCicerone() = ciceroneHolder.getCicerone(getContainerName())

    abstract fun getContainerName(): String

    abstract fun intiDefaultScreen(): Unit

    override fun onResume() {
        super.onResume()
        navigator?.let {
            getCicerone().navigatorHolder.setNavigator(it)
        }
    }
    override fun onPause() {
        getCicerone().navigatorHolder.removeNavigator()
        super.onPause()
    }

    override fun getRouter(): Router = getCicerone().router

    override fun onBackPressed(): Boolean {

        if (ftcContainer != null) {
            val fragment =
                childFragmentManager.findFragmentById(ftcContainer!!)
            return if (fragment != null && fragment is BackButtonListener
                && (fragment as BackButtonListener).onBackPressed()
            ) {
                true
            } else {
                (activity as RouterProvider?)!!.router.exit()
                true
            }
        } else {
            return false
        }

    }
}