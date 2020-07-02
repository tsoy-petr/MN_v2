package com.android.hootr.data_class_samples.domain

import com.android.hootr.data_class_samples.repository.AdvertsRepository
import com.android.hootr.data_class_samples.repository.IAdvertsRepository
import com.android.hootr.data_class_samples.repository.IOffersRepository
import com.android.hootr.data_class_samples.repository.OffersRepository

class FeedInteractor(
    private val offersRepo: IOffersRepository = OffersRepository(),
    private val advertsRepo: IAdvertsRepository = AdvertsRepository()
) {
    fun getFeedItems(): List<FeedItem> = offersRepo.getItems(25)
        .mapIndexed { idx, item ->
            when {
                idx % 4 == 3 -> listOf(item) + advertsRepo.getItems(1)
                else -> listOf(item)
            }
        }
        .flatten()
}