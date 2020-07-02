package com.android.hootor.ciceronemobileorders.ui.orders

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.android.hootor.ciceronemobileorders.R
import com.android.hootor.ciceronemobileorders.ui.Screens
import com.android.hootor.ciceronemobileorders.common.ui.BaseTabContainerFragment
import ru.terrakok.cicerone.android.support.SupportAppNavigator

class OrdersRootFragment : BaseTabContainerFragment() {

    override var ftcContainer: Int? = R.id.ftc_container_orders

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_oders, container, false)
    }

    override fun initNavigator(): SupportAppNavigator? =
        when(ftcContainer != null) {
            true -> SupportAppNavigator(activity!!, childFragmentManager, ftcContainer!!)
            false -> null
        }

    override fun getContainerName(): String = "Orders"

    override fun intiDefaultScreen() {
        ftcContainer?.apply {
            if (childFragmentManager.findFragmentById(this) == null) {
                getCicerone().router.replaceScreen(Screens.ListOredersScreen())
            }
        }
    }

    companion object {
        fun getNewInstance(args: Bundle?): OrdersRootFragment {
            val fragment = OrdersRootFragment()
            fragment.arguments = args
            return fragment
        }
    }

}