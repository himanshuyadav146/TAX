package com.example.tax.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Data {
    @SerializedName("Api_login")
    @Expose
    var apiLogin: List<ApiLogin>? = null

    @SerializedName("DeviceType")
    @Expose
    var deviceType: String? = null
    @SerializedName("EmailAddress")
    @Expose
    var emailAddress: String? = null
    @SerializedName("FirebaseId")
    @Expose
    var firebaseId: String? = null
    @SerializedName("id")
    @Expose
    var id: String? = null
    @SerializedName("IpAddress")
    @Expose
    var ipAddress: String? = null
    @SerializedName("IsVerify")
    @Expose
    var isVerify: String? = null
    @SerializedName("MobileNumber")
    @Expose
    var mobileNumber: String? = null
    @SerializedName("uuid")
    @Expose
    var uuid: String? = null
}