package com.example.tax.models

import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose

class Order {
    @SerializedName("uuid")
    @Expose
    var uuid: String? = null

    @SerializedName("DOB")
    @Expose
    var dOB: String? = null

    @SerializedName("UserId")
    @Expose
    var userId: String? = null

    @SerializedName("FinancialYear")
    @Expose
    var financialYear: String? = null

    @SerializedName("SourceOfIncome")
    @Expose
    var sourceOfIncome: String? = null

    @SerializedName("FirstName")
    @Expose
    var firstName: String? = null

    @SerializedName("MiddleName")
    @Expose
    var middleName: String? = null

    @SerializedName("LastName")
    @Expose
    var lastName: String? = null

    @SerializedName("Email")
    @Expose
    var email: String? = null

    @SerializedName("MobileNumber")
    @Expose
    var mobileNumber: String? = null

    @SerializedName("PanNumber")
    @Expose
    var panNumber: String? = null

    @SerializedName("AadharCardNumber")
    @Expose
    var aadharCardNumber: String? = null
}