package com.android.hootr.retrofitdemo.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Result(
    @SerializedName("name")
    @Expose
    var name: String,

    @SerializedName("alpha2_code")
    @Expose
    var alpha2Code: String,

    @SerializedName("alpha3_code")
    @Expose
    var alpha3Code: String
)
