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


    @SerializedName("financialyear")
    @Expose
    var financialyear: String? = null

    @SerializedName("firstname")
    @Expose
    var firstname: String? = null


    @SerializedName("lastname")
    @Expose
    var lastname: String? = null


    @SerializedName("mobileno")
    @Expose
    var mobileno: String? = null


    @SerializedName("dob")
    @Expose
    var dob: String? = null

    @SerializedName("emailid")
    @Expose
    var emailid: String? = null

    @SerializedName("panno")
    @Expose
    var panno: String? = null

    @SerializedName("userid")
    @Expose
    var userid: String? = null

    @SerializedName("sourceofincome")
    @Expose
    var sourceofincome: String? = null

    @SerializedName("updated_date")
    @Expose
    var updated_date: String? = null

    @SerializedName("creation_date")
    @Expose
    var creation_date: String? = null

    @SerializedName("id")
    @Expose
    var id: String? = null

    @SerializedName("status")
    @Expose
    var status: String? = null

    @SerializedName("aadharno")
    @Expose
    var aadharno: String? = null

    @SerializedName("flatno")
    @Expose
    private var blockNo: String? = null

    @SerializedName("building")
    @Expose
    private var building: String? = null

    @SerializedName("street")
    @Expose
    private var road: String? = null

    @SerializedName("locality")
    @Expose
    private var locality: String? = null

    @SerializedName("pincode")
    @Expose
    private var pincode: String? = null

    @SerializedName("city")
    @Expose
    private var town: String? = null

    @SerializedName("state")
    @Expose
    private var state: String? = null

    @SerializedName("country")
    @Expose
    private var country: String? = null

    @SerializedName("interestinc")
    @Expose
    private var interestinc: String? = null

    @SerializedName("interestonrd")
    @Expose
    private var interestonrd: String? = null

    @SerializedName("otherinc")
    @Expose
    private var otherinc: String? = null



    @SerializedName("bankname")
    @Expose
    private var bankname: String? = null

    @SerializedName("ifsccode")
    @Expose
    private var ifsccode: String? = null

    @SerializedName("accountno")
    @Expose
    private var accountno: String? = null

    @SerializedName("accounttype")
    @Expose
    private var accounttype: String? = null




    fun getBankname(): String? {
        return bankname
    }

    fun setBankname(bankname: String?) {
        this.bankname = bankname
    }
    fun getIfsccode(): String? {
        return ifsccode
    }

    fun setIfsccode(ifsccode: String?) {
        this.ifsccode = ifsccode
    }
    fun getAccountno(): String? {
        return accountno
    }

    fun setAccountno(accountno: String?) {
        this.accountno = accountno
    }
    fun getAccounttype(): String? {
        return accounttype
    }

    fun setAccounttype(accounttype: String?) {
        this.accounttype = accounttype
    }



    fun getInterestinc(): String? {
        return interestinc
    }

    fun setInterestinc(interestinc: String?) {
        this.interestinc = interestinc
    }

    fun getInterestonrd(): String? {
        return interestonrd
    }

    fun setInterestonrd(interestonrd: String?) {
        this.interestonrd = interestonrd
    }

    fun getOtherinc(): String? {
        return otherinc
    }

    fun setOtherinc(otherinc: String?) {
        this.otherinc = otherinc
    }



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




// *********************************************************************   Old model data

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
    @SerializedName("itrid")
    @Expose
    private var ItrId: String? = null

    fun getItrId(): String? {
        return ItrId
    }

    fun setItrId(itrID: String?) {
        this.ItrId = itrID
    }

    @SerializedName("BankDetailId")
    @Expose
    private var bankDetailId: String? = null

    fun getBankDetailId(): String? {
        return bankDetailId
    }

    fun setBankDetailId(bankDetailId: String) {
        this.bankDetailId = bankDetailId
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


//    @SerializedName("status")
//    @Expose
//    private var status: String? = null

    @SerializedName("message")
    @Expose
    private var message: String? = null

    @SerializedName("data")
    @Expose
    private var data: Data? = null

//    fun getStatus(): String? {
//        return status
//    }
//
//    fun setStatus(status: String?) {
//        this.status = status
//    }

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