package com.android.hootor.ciceronemobileorders.ui.selectedGoods

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.android.hootor.ciceronemobileorders.R
import com.android.hootor.ciceronemobileorders.common.RouterProvider
import com.android.hootor.ciceronemobileorders.common.ui.BaseForwardFragment


class ListCategoriesFragment : BaseForwardFragment() {

    override var toolbarTitle: String? = "Подбор категории товара"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list_categories, container, false)
    }

    override fun initToolbar(view: View) {
        toolbar = view.findViewById(R.id.toolbar_selected_categories)
    }

    override fun initRouter() {
        router = (parentFragment as RouterProvider).router
    }

    override fun onBackPressed(): Boolean {
        router?.exit()
        return true
    }

    companion object {
        @JvmStatic
        fun getNewInstance(args: Bundle? = null) =
            ListCategoriesFragment().apply {
                arguments = args
            }
    }
}