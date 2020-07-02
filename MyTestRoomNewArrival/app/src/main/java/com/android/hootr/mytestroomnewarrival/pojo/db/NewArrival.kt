package com.android.hootr.mytestroomnewarrival.pojo.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "new_arrival")
data class NewArrival(

    @PrimaryKey
    val id: String,

    val bc: String,

    val number: String,

    val date: Long
)