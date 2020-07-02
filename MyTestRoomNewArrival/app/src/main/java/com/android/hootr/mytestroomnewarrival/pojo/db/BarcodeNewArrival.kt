package com.android.hootr.mytestroomnewarrival.pojo.db

import androidx.room.Entity
import androidx.room.ForeignKey

@Entity(
    tableName = "barcode_arrival",
    foreignKeys =
    [ForeignKey(
        entity = NewArrival::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("idNewArrival"),
        onDelete = ForeignKey.CASCADE
    )
    ],
    primaryKeys = ["barcode", "idGood"]
)
data class BarcodeNewArrival(

    val barcode: String,
    val idGood: String,
    val idNewArrival: String

)