package com.example.tax.models

import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose

class ModelbankList {
    @SerializedName("status")
    @Expose
    var status: String? = null

    @SerializedName("message")
    @Expose
    var message: String? = null

    @SerializedName("data")
    @Expose
    var data: List<Data>? = null
}