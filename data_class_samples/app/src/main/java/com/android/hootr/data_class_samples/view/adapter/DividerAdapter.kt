package com.android.hootr.data_class_samples.view.adapter

import com.android.hootr.data_class_samples.R
import com.example.delegateadapter.delegate.KDelegateAdapter
import com.example.delegateadapter.delegate.diff.IComparableItem

object DividerAdapter : KDelegateAdapter<DividerViewModel>() {
    override fun getLayoutId() = R.layout.item_divider

    override fun isForViewType(items: MutableList<*>, position: Int): Boolean =
        items[position] === DividerViewModel

    override fun onBind(item: DividerViewModel, viewHolder: KViewHolder) = with(viewHolder) {
        //
    }

}

object DividerViewModel : IComparableItem {
    override fun id(): Any = this
    override fun content(): Any = this
}