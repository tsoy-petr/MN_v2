package com.android.hootor.mn_v2.ui.orders

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.android.hootor.mn_v2.R
import com.android.hootor.mn_v2.ui.NavScreens
import com.android.hootor.mn_v2.ui.common.navigation.BackButtonListener
import com.android.hootor.mn_v2.ui.common.navigation.RouterProvider
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.Router
import ru.terrakok.cicerone.android.support.SupportAppNavigator

class OrderActivity : AppCompatActivity(), RouterProvider {

    private var uuid: String = ""

    private val navigator by lazy {
        SupportAppNavigator(
            this,
            supportFragmentManager,
            R.id.ab_container
        )
    }

    private val cicerone = Cicerone.create()
    private val navigationHolder = cicerone.navigatorHolder

    companion object {
        const val KEY_UUID_ORDER = "uuidOrder"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order)

        intent?.extras?.let {
            uuid = it.getString(KEY_UUID_ORDER, "")
        } ?: finish()

        if (supportFragmentManager.findFragmentById(R.id.ab_container) == null) {
            router.replaceScreen(NavScreens.OrderScreen())
        }

    }

    override fun onResume() {
        super.onResume()
        navigationHolder.setNavigator(navigator)
    }

    override fun onPause() {
        navigationHolder.removeNavigator()
        super.onPause()
    }

    override fun onBackPressed() {

        val fragment =
            supportFragmentManager.findFragmentById(R.id.ab_container)
        if (fragment != null && fragment is BackButtonListener
            && (fragment as BackButtonListener).onBackPressed()
        ) {
            return
        } else {
            super.onBackPressed()
        }
//        var fragment: Fragment? = null
//        val fragments = supportFragmentManager.fragments
//        if (fragments != null) {
//            for (f in fragments) {
//                if (f.isVisible) {
//                    fragment = f
//                    break
//                }
//            }
//        }
//
//        if (fragment != null && fragment is BackButtonListener
//            && (fragment as BackButtonListener).onBackPressed()
//        ) {
//            return
//        } else {
//            router.exit()
//
//        }
//                    super.onBackPressed()
    }

    override fun getRouter(): Router = cicerone.router
}