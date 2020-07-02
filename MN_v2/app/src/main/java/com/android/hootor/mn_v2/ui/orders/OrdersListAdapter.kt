package com.android.hootor.mn_v2.ui.orders

import com.android.hootor.mn_v2.R
import com.android.hootor.mn_v2.domain.usecases.ordersList.model.OrderListModel
import com.example.delegateadapter.delegate.KDelegateAdapter
import com.example.delegateadapter.delegate.diff.IComparableItem
import kotlinx.android.synthetic.main.item_orders_list.*

class OrdersListAdapter(private val onClick: (payload: OrderListModel) -> Unit) :
    KDelegateAdapter<OrdersListItemViewModel>() {

    override fun getLayoutId(): Int = R.layout.item_orders_list

    override fun isForViewType(items: MutableList<*>, position: Int): Boolean =
        items[position] is OrdersListItemViewModel

    override fun onBind(item: OrdersListItemViewModel, viewHolder: KViewHolder) = with(viewHolder) {
        tvTitleOrder.text = item.title
        viewHolder.itemView.setOnClickListener {
            onClick(item.payload)
        }
    }
}


data class OrdersListItemViewModel(
    val title: String,
    val payload: OrderListModel
) : IComparableItem {
    override fun id(): Any = this::class.java

    override fun content(): Any = this
}