package com.example.tax.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class ApiLogin : Serializable{
    @SerializedName("UserId")
    @Expose
    var userId: String? = null
    @SerializedName("FirstName")
    @Expose
    var firstName: String? = null
    @SerializedName("LastName")
    @Expose
    var lastName: String? = null
    @SerializedName("Email")
    @Expose
    var email: String? = null

    @SerializedName("EmailAddress")
    @Expose
    var EmailAddress: String? = null

    @SerializedName("MobileNo")
    @Expose
    var mobileNo: String? = null
    @SerializedName("STATUS")
    @Expose
    var sTATUS: String? = null

    @SerializedName("msg")
    @Expose
    var msg: String? = null



    @SerializedName("Password")
    @Expose
    var Password: String? = null


    @SerializedName("Test")
    @Expose
    var Test: String? = null


    @SerializedName("data")
    @Expose
    var data: Data? = null
    @SerializedName("message")
    @Expose
    var message: String? = null
    @SerializedName("status")
    @Expose
    var status: String? = null

    @SerializedName("MobileNumber")
    @Expose
    var MobileNumber: String? = null

    @SerializedName("IpAddress")
    @Expose
    var IpAddress: String? = null

    @SerializedName("FirebaseId")
    @Expose
    var FirebaseId: String? = null

    @SerializedName("DeviceType")
    @Expose
    var DeviceType: String? = null


    //////////////////////////////////////////////////////////////////
    //
    //  Form data model PROPERTY
    //
    /////////////////////////////////////////////////////////////////


    @SerializedName("MiddleName")
    @Expose
    var MiddleName: String? = null


    @SerializedName("Gender")
    @Expose
    var Gender: String? = null

    @SerializedName("DateOfBirth")
    @Expose
    var DateOfBirth: String? = null


    @SerializedName("PanNumber")
    @Expose
    var PanNumber: String? = null


    @SerializedName("FatherName")
    @Expose
    var FatherName: String? = null



    @SerializedName("PhoneNumber")
    @Expose
    var PhoneNumber: String? = null


    @SerializedName("AadharCardNumber")
    @Expose
    var AadharCardNumber: String? = null


    @SerializedName("ProcessMode")
    @Expose
    var ProcessMode: String? = null



    @SerializedName("IsSalary")
    @Expose
    var IsSalary: String? = null



    @SerializedName("IsBusiness")
    @Expose
    var IsBusiness: String? = null


    @SerializedName("IsHouseProperty")
    @Expose
    var IsHouseProperty: String? = null


    @SerializedName("IsCapitalGain")
    @Expose
    var IsCapitalGain: String? = null


    @SerializedName("IsOtherSource")
    @Expose
    var IsOtherSource: String? = null


    @SerializedName("IsForignIncome")
    @Expose
    var IsForignIncome: String? = null


    @SerializedName("FlatNumber")
    @Expose
    var FlatNumber: String? = null


    @SerializedName("Street")
    @Expose
    var Street: String? = null

    @SerializedName("Area")
    @Expose
    var Area: String? = null

    @SerializedName("City")
    @Expose
    var City: String? = null


    @SerializedName("StateId")
    @Expose
    var StateId: String? = null

    @SerializedName("CountryId")
    @Expose
    var CountryId: String? = null

    @SerializedName("PinCode")
    @Expose
    var PinCode: String? = null


    @SerializedName("BankAccountNumber")
    @Expose
    var BankAccountNumber: String? = null

    @SerializedName("IFSCCode")
    @Expose
    var IFSCCode: String? = null

    @SerializedName("TypeOfBankAccount")
    @Expose
    var TypeOfBankAccount: String? = null


    @SerializedName("NameOfYourBank")
    @Expose
    var NameOfYourBank: String? = null


    @SerializedName("BankAccountTypeFlag")
    @Expose
    var BankAccountTypeFlag: String? = null


    @SerializedName("Form16Url")
    @Expose
    var Form16Url: String? = null



    @SerializedName("Form16Url2")
    @Expose
    var Form16Url2: String? = null

    @SerializedName("AdhaarUrl")
    @Expose
    var AdhaarUrl: String? = null


    @SerializedName("OtherDocUrl")
    @Expose
    var OtherDocUrl: String? = null


    @SerializedName("PanUrl")
    @Expose
    var PanUrl: String? = null




















    private constructor (){
    }
    companion object {
        val instance: ApiLogin by lazy { ApiLogin() }
    }
}