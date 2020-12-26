package com.example.tax.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Data {
    @SerializedName("Api_login")
    @Expose
    var apiLogin: List<ApiLogin>? = null

}