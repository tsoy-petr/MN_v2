package com.android.hootr.data_class_samples.view.adapter

import com.android.hootr.data_class_samples.R
import com.android.hootr.data_class_samples.domain.FeedItem
import com.example.delegateadapter.delegate.KDelegateAdapter
import com.example.delegateadapter.delegate.diff.IComparableItem
import kotlinx.android.synthetic.main.item_advert.*

class AdvertAdapter(
    private val onClick: (payload: FeedItem.Advert) -> Unit
) : KDelegateAdapter<AdvertViewModel>() {
    override fun getLayoutId() = R.layout.item_advert

    override fun isForViewType(items: MutableList<*>, position: Int): Boolean =
        items[position] is AdvertViewModel


    override fun onBind(item: AdvertViewModel, viewHolder: KViewHolder) = with(viewHolder) {
        bHide.setOnClickListener { onClick(item.payLoad) }
    }

}

data class AdvertViewModel(
    val payLoad: FeedItem.Advert
): IComparableItem {
    override fun id(): Any = this::class.java

    override fun content(): Any = this
}
