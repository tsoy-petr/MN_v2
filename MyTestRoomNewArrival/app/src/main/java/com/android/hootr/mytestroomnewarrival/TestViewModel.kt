package com.android.hootr.mytestroomnewarrival

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.android.hootr.mytestroomnewarrival.database.AppDatabase
import com.android.hootr.mytestroomnewarrival.pojo.db.GoodsNewArrival
import com.android.hootr.mytestroomnewarrival.pojo.db.NewArrival
import com.android.hootr.mytestroomnewarrival.pojo.db.Series
import java.util.*
import kotlin.collections.ArrayList

class TestViewModel(application: Application) : AndroidViewModel(application) {

    private val db = AppDatabase.getInstance(application)

    val listNewArrival = db.newArrivalDao().getNewArrivalList()

    val listFullArrival = db.newArrivalDao().getFullNewArrival("374583765837568375")

    fun testInsertData() {

        val newArrivalUUID = UUID.randomUUID().toString()
        val goodUUID = UUID.randomUUID().toString()
        val seriesUUID = UUID.randomUUID().toString()

        val listNewArrival = ArrayList<NewArrival>()

        listNewArrival.add(
            NewArrival(
                id = newArrivalUUID,
                bc = "374583765837568375",
                number = "ВА-000099",
                date = 1580682589000L
            )
        )

        val listGoodsNewArrival = ArrayList<GoodsNewArrival>()
        listGoodsNewArrival.add(
            GoodsNewArrival(
                newArrivalUUID,
                goodUUID,
                "Товар 1",
                23.00,
                24.00,
                seriesUUID
            )
        )

        val listSeries = ArrayList<Series>()
        listSeries.add(
            Series(
                idNewArrival =  newArrivalUUID,
                idSeries = seriesUUID,
                nameSeries = "Италия, 02.02.2021",
                shelfLife = 1612304989000L
            )
        )

        db.newArrivalDao().updateFullNewArrival(
            listNewArrival, listSeries, listGoodsNewArrival
        )
    }
}