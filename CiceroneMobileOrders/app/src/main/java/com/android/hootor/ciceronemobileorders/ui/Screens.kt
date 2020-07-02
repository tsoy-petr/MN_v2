package com.android.hootor.ciceronemobileorders.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.android.hootor.ciceronemobileorders.common.ui.ForwardFragment
import com.android.hootor.ciceronemobileorders.common.ui.TabContainerFragment
import com.android.hootor.ciceronemobileorders.ui.orders.ItemOrderFragment
import com.android.hootor.ciceronemobileorders.ui.orders.ListOrdersFragment
import com.android.hootor.ciceronemobileorders.ui.orders.OrdersRootFragment
import com.android.hootor.ciceronemobileorders.ui.selectedGoods.ListCategoriesFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

object Screens {

    class ForwardScreen(val name: String?, val number: Int?): SupportAppScreen() {
        override fun getFragment(): Fragment? = ForwardFragment.getNewInstance(name, number)
    }

    class TabScreen(val tabName: String) : SupportAppScreen() {
        override fun getFragment(): Fragment? {
            return TabContainerFragment.getNewInstance(tabName)
        }
    }

    class OrdersScreen(val args: Bundle? = null) : SupportAppScreen() {
        override fun getFragment(): Fragment? {
            return OrdersRootFragment.getNewInstance(args)
        }
    }

    class ListOredersScreen(val args: Bundle? = null) : SupportAppScreen() {
        override fun getFragment(): Fragment? {
            return ListOrdersFragment.getNewInstance(args)
        }
    }

    class ItemOredersScreen(val args: Bundle? = null) : SupportAppScreen() {
        override fun getFragment(): Fragment? {
            return ItemOrderFragment.getNewInstance(args)
        }
    }

    class ListCategoriesSelected(val args: Bundle? = null) : SupportAppScreen() {
        override fun getFragment(): Fragment? {
            return ListCategoriesFragment.getNewInstance(args)
        }
    }

}