package com.android.hootr.mytestroomnewarrival.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.android.hootr.mytestroomnewarrival.pojo.api.NewArrivalApi
import com.android.hootr.mytestroomnewarrival.pojo.db.GoodsNewArrival
import com.android.hootr.mytestroomnewarrival.pojo.db.NewArrival
import com.android.hootr.mytestroomnewarrival.pojo.db.Series

@Dao
abstract class NewArrivalDao {

    @Query("SELECT * FROM new_arrival")
    abstract fun getNewArrivalList(): LiveData<List<NewArrival>>

    @Query("SELECT * FROM new_arrival WHERE bc == :bc")
    abstract fun getFullNewArrival(bc: String): LiveData<NewArrivalApi>

    @Transaction
    open fun updateFullNewArrival(
        listNewArrival: List<NewArrival>,
        listSeries: List<Series>,
        listGoodsNewArrival: List<GoodsNewArrival>
    ) {

        inserNewArrival(listNewArrival)
        insertGoodsNewArrival(listGoodsNewArrival)
        insertSeries(listSeries)

    }

    @Insert
    abstract fun inserNewArrival(listNewArrival: List<NewArrival>)

    @Insert
    abstract fun insertGoodsNewArrival(listGoodsNewArrival: List<GoodsNewArrival>)

    @Insert
    abstract fun insertSeries(listSeries: List<Series>)
}