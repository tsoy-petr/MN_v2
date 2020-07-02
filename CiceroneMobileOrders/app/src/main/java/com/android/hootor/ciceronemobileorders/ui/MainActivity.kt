package com.android.hootor.ciceronemobileorders.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.android.hootor.ciceronemobileorders.R
import com.android.hootor.ciceronemobileorders.common.BackButtonListener
import com.android.hootor.ciceronemobileorders.common.RouterProvider
import com.ashokvarma.bottomnavigation.BottomNavigationBar
import com.ashokvarma.bottomnavigation.BottomNavigationItem
import com.ashokvarma.bottomnavigation.TextBadgeItem
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.Router


class MainActivity : AppCompatActivity(), RouterProvider {

    private var lastSelectedPosition = 0
    private val cicerone = Cicerone.create()
    private val navigationHolder = cicerone.navigatorHolder

    private lateinit var bottomNavigationBar: BottomNavigationBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        savedInstanceState?.let {lastSelectedPosition = it.getInt(LAST_SELECTED_POSITION, 0)}

        intiViews()

        bottomNavigationBar.selectTab(lastSelectedPosition, true)

    }

    private fun intiViews() {

        bottomNavigationBar = findViewById(R.id.ab_bottom_navigation_bar)

        val numberBadgeItem = TextBadgeItem()

        bottomNavigationBar.clearAll()
        bottomNavigationBar.setMode(BottomNavigationBar.MODE_DEFAULT)
        bottomNavigationBar.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_DEFAULT)
        bottomNavigationBar
            .addItem(
                BottomNavigationItem(
                    R.drawable.ic_home_24,
                    "Home"
                ).setActiveColorResource(R.color.colorPrimary).setBadgeItem(numberBadgeItem)
            )
            .addItem(BottomNavigationItem(R.drawable.ic_directions_run_24, "List"))
            .addItem(BottomNavigationItem(R.drawable.ic_settings_24, "Settings"))
            .initialise()
        bottomNavigationBar.setTabSelectedListener(object :
            BottomNavigationBar.OnTabSelectedListener {
            override fun onTabSelected(position: Int) {
                lastSelectedPosition = position
                when (position) {
                    0 -> {
                        selectTab("Home")
                    }
                    1 -> {
                        selectTab("List")
                    }
                    2 -> {
                        selectTab("Settings")
                    }
                }
            }

            override fun onTabReselected(position: Int) {}
            override fun onTabUnselected(position: Int) {}
        })
    }

    private fun selectTab(tab: String) {

        var currentFragment: Fragment? = null

        val fragments = supportFragmentManager.fragments
        fragments?.let {
            it.filter {
                it.isVisible
            }.map { fr ->
                fr?.let {
                    currentFragment = fr
                }
            }
        }

        val newFragment = supportFragmentManager.findFragmentByTag(tab)
        val transaction = supportFragmentManager.beginTransaction()

        if (!(currentFragment != null && newFragment != null && currentFragment == newFragment)) {
            if (newFragment == null) {
                if (tab == "Home") {
                    transaction.add(R.id.ab_container, Screens.OrdersScreen().fragment!!, tab)
                } else {
                    transaction.add(R.id.ab_container, Screens.TabScreen(tab).fragment!!, tab)
                }
            }
            if (currentFragment != null) {
                transaction.hide(currentFragment!!)
            }
            if (newFragment != null) {
                transaction.show(newFragment)
            }
            transaction.commit()
        }
    }

    override fun onBackPressed() {
        val fm = supportFragmentManager
        var fragment: Fragment? = null
        val fragments = fm.fragments
        if (fragments != null) {
            for (f in fragments) {
                if (f.isVisible) {
                    fragment = f
                    break
                }
            }
        }
        if (fragment != null && fragment is BackButtonListener
            && (fragment as BackButtonListener).onBackPressed()
        ) {
            return
        } else {
            router.exit()
        }

    }

    override fun getRouter(): Router = cicerone.router

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(LAST_SELECTED_POSITION, lastSelectedPosition)
    }
    companion object {
        const val LAST_SELECTED_POSITION = "lastSelectedPosition"
    }
}