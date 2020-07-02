package com.android.hootr.retrofitdemo.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class RestResponse(
//    var f: String  = ""
    @SerializedName("messages")
    @Expose
    var messages: ArrayList<String>
    ,
    @SerializedName("result")
    @Expose
    var result: ArrayList<Result>
)