package com.example.tax.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class LoginModel {
    @SerializedName("data")
    @Expose
    var data: Data? = null

}