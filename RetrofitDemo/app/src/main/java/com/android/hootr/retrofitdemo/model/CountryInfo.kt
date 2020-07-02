package com.android.hootr.retrofitdemo.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CountryInfo(
    @SerializedName("RestResponse")
    @Expose
    var restResponse: RestResponse
)