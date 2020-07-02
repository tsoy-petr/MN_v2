package com.android.hootor.ciceronemobileorders.common.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.android.hootor.ciceronemobileorders.common.BackButtonListener
import com.android.hootor.ciceronemobileorders.common.RouterProvider
import ru.terrakok.cicerone.Router

abstract class BaseForwardFragment: Fragment(), BackButtonListener {

    abstract var toolbarTitle: String?

    var toolbar: Toolbar? = null
    var router: Router? = null

    abstract fun initToolbar(view: View)
    abstract fun initRouter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRouter()
        initToolbar(view)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        toolbar?.apply {
            title = toolbarTitle
            setNavigationOnClickListener {
                router?.exit()
            }
        }
    }

    abstract override fun onBackPressed(): Boolean
}