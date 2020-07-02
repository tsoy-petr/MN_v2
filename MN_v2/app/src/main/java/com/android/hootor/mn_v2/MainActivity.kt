package com.android.hootor.mn_v2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.android.hootor.mn_v2.ui.NavScreens
import com.android.hootor.mn_v2.ui.common.navigation.BackButtonListener
import com.android.hootor.mn_v2.ui.common.navigation.RouterProvider
import com.ashokvarma.bottomnavigation.BottomNavigationBar
import com.ashokvarma.bottomnavigation.BottomNavigationItem
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.Router
import ru.terrakok.cicerone.android.support.SupportAppNavigator
import ru.terrakok.cicerone.android.support.SupportAppScreen

class MainActivity : AppCompatActivity(), RouterProvider {

    private var lastSelectedPosition = 0
    private val cicerone = Cicerone.create()
    private val navigationHolder = cicerone.navigatorHolder

    private val navigator by lazy {
        SupportAppNavigator(
            this,
            supportFragmentManager,
            R.id.ab_container
        )
    }

    private lateinit var bottomNavigationBar: BottomNavigationBar

    companion object {
        const val LAST_SELECTED_POSITION = "lastSelectedPosition"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        savedInstanceState?.let { lastSelectedPosition = it.getInt(LAST_SELECTED_POSITION, 0) }

        initView()

        bottomNavigationBar.selectTab(lastSelectedPosition, true)
    }

    private fun initView() {

        bottomNavigationBar = findViewById(R.id.ab_bottom_navigation_bar)
        bottomNavigationBar.selectTab(lastSelectedPosition, true)

        bottomNavigationBar.clearAll()
        bottomNavigationBar.setMode(BottomNavigationBar.MODE_DEFAULT)
        bottomNavigationBar.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_DEFAULT)
        bottomNavigationBar
            .addItem(BottomNavigationItem(R.drawable.ic_orders_tab_24, "Orders"))
            .addItem(BottomNavigationItem(R.drawable.ic_agent_route_24, "Agent route"))
            .addItem(BottomNavigationItem(R.drawable.ic_settings_24, "Settings"))
            .addItem(BottomNavigationItem(R.drawable.ic_sync_24, "Sync"))
            .initialise()
        bottomNavigationBar.setTabSelectedListener(object :
            BottomNavigationBar.OnTabSelectedListener {
            override fun onTabSelected(position: Int) {
                lastSelectedPosition = position
                when (position) {
                    0 -> selectTab("Orders")
                    1 -> selectTab("AgentRoute")
                    2 -> selectTab("Settings")
                    3 -> selectTab("Sync")
                }
            }

            override fun onTabReselected(position: Int) {}
            override fun onTabUnselected(position: Int) {}
        })

    }

    private fun selectTab(tab: String) {

        var currentFragment: Fragment? = null

        val fragments = supportFragmentManager.fragments
        fragments.let {
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
                getScreenTab(tab).fragment?.let {
                    transaction.add(R.id.ab_container, it, tab)
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

    private fun getScreenTab(tab: String): SupportAppScreen = when (tab) {
        "Orders" -> NavScreens.OrdersListScreen()
        "AgentRoute" -> NavScreens.AgentRouteListScreen()
        "Settings" -> NavScreens.SettingsScreen()
        "Sync" -> NavScreens.SyncScreen()
        else -> object : SupportAppScreen() {}
    }

    override fun onBackPressed() {
        val fm = supportFragmentManager
        var fragment: Fragment? = null
        val fragments = fm.fragments
        for (f in fragments) {
            if (f.isVisible) {
                fragment = f
                break
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

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(LAST_SELECTED_POSITION, lastSelectedPosition)
    }

    override fun onResume() {
        super.onResume()
        navigationHolder.setNavigator(navigator)
    }

    override fun onPause() {
        navigationHolder.removeNavigator()
        super.onPause()
    }

    override fun getRouter(): Router = cicerone.router
}