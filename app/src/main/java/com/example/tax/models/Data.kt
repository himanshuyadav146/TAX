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


    @SerializedName("PanNumber")
    @Expose
    private var panNumber: String? = null

    @SerializedName("AadharCardNumber")
    @Expose
    private var aadharCardNumber: String? = null

    @SerializedName("DOB")
    @Expose
    private var dOB: String? = null

    @SerializedName("SourceOfIncome")
    @Expose
    private var sourceOfIncome: SourceOfIncome? = null

    @SerializedName("CreateAt")
    @Expose
    private var createAt: String? = null

    @SerializedName("UpdatedAt")
    @Expose
    private var updatedAt: Any? = null

    @SerializedName("DeletedAt")
    @Expose
    private var deletedAt: Any? = null


    @SerializedName("BlockNo")
    @Expose
    private var blockNo: String? = null

    @SerializedName("Building")
    @Expose
    private var building: String? = null

    @SerializedName("Road")
    @Expose
    private var road: String? = null

    @SerializedName("Locality")
    @Expose
    private var locality: String? = null

    @SerializedName("Pincode")
    @Expose
    private var pincode: String? = null

    @SerializedName("Town")
    @Expose
    private var town: String? = null

    @SerializedName("State")
    @Expose
    private var state: String? = null

    @SerializedName("Country")
    @Expose
    private var country: String? = null

    @SerializedName("ItrId")
    @Expose
    private var itrId: String? = null

    @SerializedName("Type")
    @Expose
    private var type: String? = null

    fun getBlockNo(): String? {
        return blockNo
    }

    fun setBlockNo(blockNo: String?) {
        this.blockNo = blockNo
    }

    fun getBuilding(): String? {
        return building
    }

    fun setBuilding(building: String?) {
        this.building = building
    }

    fun getRoad(): String? {
        return road
    }

    fun setRoad(road: String?) {
        this.road = road
    }

    fun getLocality(): String? {
        return locality
    }

    fun setLocality(locality: String?) {
        this.locality = locality
    }

    fun getPincode(): String? {
        return pincode
    }

    fun setPincode(pincode: String?) {
        this.pincode = pincode
    }

    fun getTown(): String? {
        return town
    }

    fun setTown(town: String?) {
        this.town = town
    }

    fun getState(): String? {
        return state
    }

    fun setState(state: String?) {
        this.state = state
    }

    fun getCountry(): String? {
        return country
    }

    fun setCountry(country: String?) {
        this.country = country
    }

    fun getItrId(): String? {
        return itrId
    }

    fun setItrId(itrId: String?) {
        this.itrId = itrId
    }

    fun getType(): String? {
        return type
    }

    fun setType(type: String?) {
        this.type = type
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

    fun getSourceOfIncome(): SourceOfIncome? {
        return sourceOfIncome
    }

    fun setSourceOfIncome(sourceOfIncome: SourceOfIncome?) {
        this.sourceOfIncome = sourceOfIncome
    }

    fun getCreateAt(): String? {
        return createAt
    }

    fun setCreateAt(createAt: String?) {
        this.createAt = createAt
    }

    fun getUpdatedAt(): Any? {
        return updatedAt
    }

    fun setUpdatedAt(updatedAt: Any?) {
        this.updatedAt = updatedAt
    }

    fun getDeletedAt(): Any? {
        return deletedAt
    }

    fun setDeletedAt(deletedAt: Any?) {
        this.deletedAt = deletedAt
    }
}