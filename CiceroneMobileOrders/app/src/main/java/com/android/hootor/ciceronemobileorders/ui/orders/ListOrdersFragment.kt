package com.android.hootor.ciceronemobileorders.ui.orders

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.android.hootor.ciceronemobileorders.R
import com.android.hootor.ciceronemobileorders.common.RouterProvider
import com.android.hootor.ciceronemobileorders.common.ui.BaseForwardFragment
import com.android.hootor.ciceronemobileorders.ui.Screens
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ListOrdersFragment() : BaseForwardFragment() {

    override var toolbarTitle: String? = "Заказы"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_list_orders, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val fabAdd = view.findViewById<FloatingActionButton>(R.id.fab_add_list_orders)
        fabAdd.setOnClickListener {
            router?.navigateTo(Screens.ItemOredersScreen())
        }

    }

    override fun initRouter() {
        router = (parentFragment as RouterProvider).router
    }
    override fun initToolbar(view: View) {
        toolbar = view.findViewById(R.id.toolbar_list_orders)
    }

    companion object {

        @Override
        @JvmStatic
        fun getNewInstance(args: Bundle?) =
            ListOrdersFragment().apply {
                arguments = args
            }
    }

    override fun onBackPressed(): Boolean {
        router?.exit()
        return true
    }
}
