package com.example.tax.models

import com.google.gson.annotations.SerializedName

data class ArrayBaseModel(
    @SerializedName("data") val data : List<Data>,
    @SerializedName("status") val status : String) {
}