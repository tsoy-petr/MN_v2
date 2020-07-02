package com.android.hootr.data_class_samples.repository

import com.android.hootr.data_class_samples.domain.FeedItem
import kotlin.random.Random

interface IOffersRepository {
    fun getItems(count: Int): List<FeedItem.Offer>
}

class OffersRepository : IOffersRepository {
    override fun getItems(count: Int): List<FeedItem.Offer> = (0 until count).map { id ->
        when (Random.nextInt(3)) {
            0 -> AUDI
            1 -> BMW
            else -> LADA
        }.copy(id = "$id")
    }

    companion object {

        private val AUDI = FeedItem.Offer(
            id = "",
            title = "Audi A5, 2017",
            text = "Машина в идеальном состоянии",
            price = 300000000
        )
        private val BMW = FeedItem.Offer(
            id = "",
            title = "BMW X6, 2019",
            text = "Машина только что из салона",
            price = 400000000
        )
        private val LADA = FeedItem.Offer(
            id = "",
            title = "Лада ВАЗ, 1999",
            text = "Ведро с болтами",
            price = 20000
        )

    }
}