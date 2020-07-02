package com.android.hootor.mn_v2.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.android.hootor.mn_v2.domain.usecases.ordersList.model.OrderListModel
import com.android.hootor.mn_v2.ui.orders.OrderActivity
import com.android.hootor.mn_v2.ui.orders.OrderFragment
import com.android.hootor.mn_v2.ui.orders.OrdersListFragment
import com.android.hootor.mn_v2.ui.synchronization.SynchronizationFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

object NavScreens {

    class OrdersListScreen(val args: Bundle? = null) : SupportAppScreen() {
        override fun getFragment(): Fragment? {
            return OrdersListFragment.newInstance(args)
        }
    }

    class OrderScreen(val args: Bundle? = null) : SupportAppScreen() {
        override fun getFragment(): Fragment? {
            return OrderFragment.newInstance(args)
        }
    }

    class OrderRootScreen(val order: OrderListModel) : SupportAppScreen() {
        override fun getActivityIntent(context: Context): Intent? {
            val intent = Intent(context, OrderActivity::class.java).apply {
                putExtra(OrderActivity.KEY_UUID_ORDER, order.uuid)
            }
            return intent
        }
    }

    class AgentRouteListScreen(val args: Bundle? = null) : SupportAppScreen() {
        override fun getFragment(): Fragment? {
            return super.getFragment()
        }
    }

    class SettingsScreen(val args: Bundle? = null) : SupportAppScreen() {
        override fun getFragment(): Fragment? {
            return super.getFragment()
        }
    }

    class OrederScreeen(val args: Bundle? = null) : SupportAppScreen() {
        override fun getFragment(): Fragment? {
            return OrderFragment.newInstance(args)
        }
    }

    class SyncScreen(val args: Bundle? = null) : SupportAppScreen() {
        override fun getFragment(): Fragment? {
            return SynchronizationFragment.newInstance(args)
        }
    }


}