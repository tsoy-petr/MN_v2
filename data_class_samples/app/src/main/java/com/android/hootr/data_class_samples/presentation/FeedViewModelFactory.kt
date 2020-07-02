package com.android.hootr.data_class_samples.presentation

import com.android.hootr.data_class_samples.domain.FeedItem
import com.android.hootr.data_class_samples.view.adapter.AdvertViewModel
import com.android.hootr.data_class_samples.view.adapter.DividerViewModel
import com.android.hootr.data_class_samples.view.adapter.OfferViewModel
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.*

class FeedViewModelFactory {

    private val decimalForma = DecimalFormat("###,###.#")

    fun toViewModel(model: FeelPresentationModel.Model): FeedViewModel {
        val items = model.items.map { item ->
            when (item) {
                is FeedItem.Offer -> item.toViewModel()
                is FeedItem.Advert -> item.toViewModel()
            }
        }

        val itemsWithDividers = items
            .map { item -> listOf(item, DividerViewModel) }
            .flatten()

        return FeedViewModel(items = itemsWithDividers)
    }

    private fun FeedItem.Offer.toViewModel(): OfferViewModel = OfferViewModel(
        title = title,
        text = text,
        price = decimalForma.format(price) + " р.",
        payLoad = this
    )

    private fun FeedItem.Advert.toViewModel(): AdvertViewModel = AdvertViewModel(
        payLoad = this
    )
}