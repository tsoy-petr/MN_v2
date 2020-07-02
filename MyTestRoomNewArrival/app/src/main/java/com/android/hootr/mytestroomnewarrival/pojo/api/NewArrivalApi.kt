package com.android.hootr.mytestroomnewarrival.pojo.api

import androidx.room.Embedded
import androidx.room.Relation
import com.android.hootr.mytestroomnewarrival.pojo.db.GoodsNewArrival
import com.android.hootr.mytestroomnewarrival.pojo.db.NewArrival
import com.android.hootr.mytestroomnewarrival.pojo.db.Series

data class NewArrivalApi(
    @Embedded val newArrival: NewArrival,
    @Relation(
        parentColumn = "id", entity = GoodsNewArrival::class, entityColumn = "idNewArrival"
    ) val goods: List<GoodsNewArrival>,
    @Relation(
        parentColumn = "id",
        entity = Series::class,
        entityColumn = "idNewArrival"
    ) val series: List<Series>)