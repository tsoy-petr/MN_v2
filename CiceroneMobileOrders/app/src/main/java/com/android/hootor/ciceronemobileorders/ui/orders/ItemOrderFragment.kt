package com.android.hootor.ciceronemobileorders.ui.orders

import android.content.DialogInterface
import android.os.Build
import android.os.Bundle
import android.provider.Contacts.People
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.TextView
import android.widget.Toast
import com.android.hootor.ciceronemobileorders.R
import com.android.hootor.ciceronemobileorders.common.RouterProvider
import com.android.hootor.ciceronemobileorders.common.ui.BaseForwardFragment
import com.android.hootor.ciceronemobileorders.ui.Screens
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.floatingactionbutton.FloatingActionButton


class ItemOrderFragment : BaseForwardFragment(){

    override var toolbarTitle: String? = "Заказ"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_item_order, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val fabAddGoods = view.findViewById<FloatingActionButton>(R.id.fab_add_goods)
        fabAddGoods.setOnClickListener {
            router?.navigateTo(Screens.ListCategoriesSelected())
        }
    }

    override fun initToolbar(view: View) {
        toolbar = view.findViewById(R.id.toolbar_item_orders)
    }

    override fun initRouter() {
        router = (parentFragment as RouterProvider).router
    }

    override fun onBackPressed(): Boolean {
//        router?.exit()
        return true
    }

    private fun showBottomSheetDialog(people: People) {
        if (mBehavior.getState() === BottomSheetBehavior.STATE_EXPANDED) {
            mBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED)
        }
        val view: View = layoutInflater.inflate(R.layout.sheet_basic, null)
        (view.findViewById<View>(R.id.name) as TextView).setText(people.name)
        (view.findViewById<View>(R.id.address) as TextView).setText(R.string.middle_lorem_ipsum)
        view.findViewById<View>(R.id.bt_close).setOnClickListener { mBottomSheetDialog.dismiss() }
        view.findViewById<View>(R.id.bt_details).setOnClickListener {
            Toast.makeText(
                ApplicationProvider.getApplicationContext(),
                "Details clicked",
                Toast.LENGTH_SHORT
            ).show()
        }
        mBottomSheetDialog = BottomSheetDialog(this)
        mBottomSheetDialog.setContentView(view)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mBottomSheetDialog.getWindow()
                .addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        }
        mBottomSheetDialog.show()
        mBottomSheetDialog.setOnDismissListener(DialogInterface.OnDismissListener {
            mBottomSheetDialog = null
        })
    }

    companion object {

        @JvmStatic
        fun getNewInstance(args: Bundle? = null) =
            ItemOrderFragment().apply {
                arguments = args
            }
    }
}