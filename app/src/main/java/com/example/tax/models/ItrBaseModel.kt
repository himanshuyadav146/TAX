package com.example.tax.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable


class ItrBaseModel : Serializable {
    private constructor (){
    }
    companion object {
        val instance: ItrBaseModel by lazy { ItrBaseModel() }
    }


    @SerializedName("UserId")
    @Expose
    private var userId: String? = null

    @SerializedName("FinancialYear")
    @Expose
    private var financialYear: String? = null

    @SerializedName("FirstName")
    @Expose
    private var firstName: String? = null

    @SerializedName("MiddleName")
    @Expose
    private var middleName: String? = null

    @SerializedName("LastName")
    @Expose
    private var lastName: String? = null

    @SerializedName("Email")
    @Expose
    private var email: String? = null

    @SerializedName("MobileNumber")
    @Expose
    private var mobileNumber: String? = null

    @SerializedName("PanNumber")
    @Expose
    private var panNumber: String? = null

    @SerializedName("AadharCardNumber")
    @Expose
    private var aadharCardNumber: String? = null

    @SerializedName("DOB")
    @Expose
    private var dOB: String? = null

    @SerializedName("Type")
    @Expose
    private var type: String? = null

    @SerializedName("OrderId")
    @Expose
    private var orderId: String? = null

    @SerializedName("SourceOfIncome")
    @Expose
    private var SourceOfIncome: SourceOfIncome? = null
    @SerializedName("ItrId")
    @Expose
    private var ItrId: String? = null

    fun getItrId(): String? {
        return ItrId
    }

    fun setItrId(itrID: String?) {
        this.ItrId = itrID
    }

    fun getUserId(): String? {
        return userId
    }

    fun setUserId(userId: String?) {
        this.userId = userId
    }

    fun getFinancialYear(): String? {
        return financialYear
    }

    fun setFinancialYear(financialYear: String?) {
        this.financialYear = financialYear
    }

    fun getFirstName(): String? {
        return firstName
    }

    fun setFirstName(firstName: String?) {
        this.firstName = firstName
    }

    fun getMiddleName(): String? {
        return middleName
    }

    fun setMiddleName(middleName: String?) {
        this.middleName = middleName
    }

    fun getLastName(): String? {
        return lastName
    }

    fun setLastName(lastName: String?) {
        this.lastName = lastName
    }

    fun getEmail(): String? {
        return email
    }

    fun setEmail(email: String?) {
        this.email = email
    }

    fun getMobileNumber(): String? {
        return mobileNumber
    }

    fun setMobileNumber(mobileNumber: String?) {
        this.mobileNumber = mobileNumber
    }

    fun getPanNumber(): String? {
        return panNumber
    }

    fun setPanNumber(panNumber: String?) {
        this.panNumber = panNumber
    }

    fun getAadharCardNumber(): String? {
        return aadharCardNumber
    }

    fun setAadharCardNumber(aadharCardNumber: String?) {
        this.aadharCardNumber = aadharCardNumber
    }

    fun getDOB(): String? {
        return dOB
    }

    fun setDOB(dOB: String?) {
        this.dOB = dOB
    }

    fun getType(): String? {
        return type
    }

    fun setType(type: String?) {
        this.type = type
    }

    fun getOrderId(): String? {
        return orderId
    }

    fun setOrderId(orderId: String?) {
        this.orderId = orderId
    }

    fun getSourceOfIncome(): SourceOfIncome? {
        return SourceOfIncome
    }

    fun setSourceOfIncome(sourceOfIncome: SourceOfIncome?) {
        this.SourceOfIncome = sourceOfIncome
    }


    @SerializedName("status")
    @Expose
    private var status: String? = null

    @SerializedName("message")
    @Expose
    private var message: String? = null

    @SerializedName("data")
    @Expose
    private var data: Data? = null

    fun getStatus(): String? {
        return status
    }

    fun setStatus(status: String?) {
        this.status = status
    }

    fun getMessage(): String? {
        return message
    }

    fun setMessage(message: String?) {
        this.message = message
    }

    fun getData(): Data? {
        return data
    }

    fun setData(data: Data?) {
        this.data = data
    }

}