package com.android.hootr.hogwartslibrary.data.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CharacterRemote(
    val _id: String,
    val name: String = "",
    val role: String = "",
    val house: String = "",
    val school: String = "",
    @SerialName("__v")
    val version: Int,
    val ministryOfMagic: Boolean,
    val orderOfThePhoenix: Boolean,
    val dumbledoresArmy: Boolean,
    val deathEater: Boolean,
    val alias: String = "",
    val bloodStatus: String = "",
    val species: String = "",
    val wand: String = "",
    val animagus:String = "",
    val patronus: String = ""
)