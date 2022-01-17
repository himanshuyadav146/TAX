package com.example.tax.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class RegistrationModel {
    @SerializedName("name")
    @Expose
    var name: String? = null

    @SerializedName("email")
    @Expose
    var email: String? = null

    @SerializedName("updated_at")
    @Expose
    var updated_at: String? = null

    @SerializedName("created_at")
    @Expose
    var created_at: String? = null

    @SerializedName("id")
    @Expose
    var id: Int? = 0

    @SerializedName("status")
    @Expose
    var status: String? = null
    @SerializedName("message")
    @Expose
    var message: String? = null

    @SerializedName("password")
    @Expose
    var password: String? = null

//    @SerializedName("mobileno")
//    @Expose
//    var mobileno: String? = null

    @SerializedName("platform")
    @Expose
    var platform: String? = null

    @SerializedName("email_verified_at")
    @Expose
    var email_verified_at: String? = null

    @SerializedName("api_token")
    @Expose
    var api_token: String? = null

    @SerializedName("phoneno")
    @Expose
    var phoneno: String? = null

    @SerializedName("token")
    @Expose
    var token: String? = null



}