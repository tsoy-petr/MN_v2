package com.android.hootr.hogwartslibrary.data.models

import kotlinx.serialization.SerialName

data class HouseRemote(
    val _id: String,
    val name: String,
    val mascot: String,
    val headOfHouse: String,
    val houseGhost: String,
    val founder: String,
    @SerialName("__v")
    val version: Int = 0,
    val school: String,
    val members: List<String>,
    val values: List<String>,
    val colors: List<String>

)