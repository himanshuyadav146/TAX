package com.example.tax.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("id") val id : Int,
    @SerializedName("interestinc") val interestinc : Int,
    @SerializedName("interestonrd") val interestonrd : Int,
    @SerializedName("otherinc") val otherinc : Int,
    @SerializedName("updated_date") val updated_date : String,
    @SerializedName("creation_date") val creation_date : String,
    @SerializedName("itrid") val itrid : Int,
    @SerializedName("bankname") val bankname : String,
    @SerializedName("ifsccode") val ifsccode : String,
    @SerializedName("accountno") val accountno : String,
    @SerializedName("accounttype") val accounttype : String,
    @SerializedName("userid") val userid : Int,
    @SerializedName("message") val message : String
) {
    }