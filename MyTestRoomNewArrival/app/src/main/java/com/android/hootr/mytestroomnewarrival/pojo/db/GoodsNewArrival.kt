package com.android.hootr.mytestroomnewarrival.pojo.db

import androidx.room.Entity
import androidx.room.ForeignKey

@Entity(
    tableName = "orders_new_arrival",
    foreignKeys =
    [ForeignKey(
        entity = NewArrival::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("idNewArrival"),
        onDelete = ForeignKey.CASCADE
    )],
    primaryKeys = ["idNewArrival", "idGood"]
)
data class GoodsNewArrival(

    val idNewArrival: String,
    val idGood: String,

    val nameGood: String,

    val actualQuantity: Double,

    val accountingAmount: Double,

    val idSeries: String
)