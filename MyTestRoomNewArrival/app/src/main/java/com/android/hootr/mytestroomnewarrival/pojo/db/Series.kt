package com.android.hootr.mytestroomnewarrival.pojo.db

import androidx.room.Entity
import androidx.room.ForeignKey

@Entity(
    tableName = "series",
    foreignKeys =
    [ForeignKey(
        entity = NewArrival::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("idNewArrival"),
        onDelete = ForeignKey.CASCADE
    )],
    primaryKeys = ["idNewArrival", "idSeries"]
)
data class Series(

    val idNewArrival: String,

    val idSeries: String,

    val nameSeries: String,

    val shelfLife: Long?
)