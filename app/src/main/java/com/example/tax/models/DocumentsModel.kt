package com.example.tax.models

import Files
import com.google.gson.annotations.SerializedName

data class DocumentsModel(
    @SerializedName("files") val files : List<Files>,
    @SerializedName("status") val status : String
)
