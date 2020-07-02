package com.android.hootr.data_class_samples.view.adapter

import com.android.hootr.data_class_samples.R
import com.android.hootr.data_class_samples.domain.FeedItem

import com.example.delegateadapter.delegate.KDelegateAdapter
import com.example.delegateadapter.delegate.diff.IComparableItem
import kotlinx.android.synthetic.main.item_offer.*

class OfferAdapter(
    private val onClick: (payload: FeedItem.Offer) -> Unit
) : KDelegateAdapter<OfferViewModel>() {
    override fun getLayoutId() = R.layout.item_offer

    override fun isForViewType(items: MutableList<*>, position: Int): Boolean =
        items[position] is OfferViewModel


    override fun onBind(item: OfferViewModel, viewHolder: KViewHolder) = with(viewHolder){
        tvTitle.text = item.title
        tvText.text = item.text
        tvPrice.text = item.price

        itemView.setOnClickListener { onClick(item.payLoad) }
    }

}

data class OfferViewModel(
    val title: String,
    val price: String,
    val text: String,
    val payLoad: FeedItem.Offer
) : IComparableItem {
    override fun id(): Any = this::class.java

    override fun content(): Any = this
}