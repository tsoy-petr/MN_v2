package com.android.hootr.data_class_samples.repository

import com.android.hootr.data_class_samples.domain.FeedItem

interface IAdvertsRepository {
    fun getItems(count: Int): List<FeedItem.Advert>
}

class AdvertsRepository : IAdvertsRepository {

    private var id: Int = 0

    override fun getItems(count: Int): List<FeedItem.Advert> = (0 until count).map {
        FeedItem.Advert(
            id = "${id++}"
        )
    }
}
