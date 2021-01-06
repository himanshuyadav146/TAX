package com.example.tax.Interfaces

import com.example.tax.models.ApiLogin
import com.example.tax.models.Data
import com.example.tax.models.LoginModel
import com.example.tax.models.RegistrationModel
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

interface API_Interface {

 //   http://sh017.hostgator.tempwebhost.net/~easytxi6/api/v1/login
    @POST("api/v1/login")
    fun postLogin(@Body objApiLogin : ApiLogin): Call<LoginModel>

    @POST("api/v1/registration")
    fun postRegistration(@Body objApiLogin : ApiLogin): Call<RegistrationModel>

    @Headers("Content-Type:multipart/form-data")
    @Multipart
    @POST("api/customer/post?")
    fun postData(@Part Form16Url: MultipartBody.Part,
                 @Part Form16Url2: MultipartBody.Part,
                 @Part PanUrl: MultipartBody.Part,
                 @Part AdhaarUrl: MultipartBody.Part,
                 @Part OtherDocUrl: MultipartBody.Part,
                   @Part("UserId") UserId : RequestBody,
                   @Part("FirstName") FirstName : RequestBody,
                   @Part("MiddleName") MiddleName : RequestBody,
                   @Part("LastName") LastName : RequestBody,
                   @Part("Gender") Gender : RequestBody,
                   @Part("DateOfBirth") DateOfBirth : RequestBody,
                   @Part("PanNumber") PanNumber : RequestBody,
                   @Part("FatherName") FatherName : RequestBody,
                   @Part("Email") Email : RequestBody,
                   @Part("PhoneNumber") PhoneNumber : RequestBody,
                   @Part("AadharCardNumber") AadharCardNumber : RequestBody,
                   @Part("ProcessMode") ProcessMode : RequestBody,
                   @Part("IsSalary") IsSalary : RequestBody,
                   @Part("IsBusiness") IsBusiness : RequestBody,
                   @Part("IsHouseProperty") IsHouseProperty : RequestBody,
                   @Part("IsCapitalGain") IsCapitalGain : RequestBody,
                   @Part("IsOtherSource") IsOtherSource : RequestBody,
                   @Part("IsForignIncome") IsForignIncome : RequestBody,
                   @Part("FlatNumber") FlatNumber : RequestBody,
                   @Part("Street") Street : RequestBody,
                   @Part("Area") Area : RequestBody,
                   @Part("City") City : RequestBody,
                   @Part("StateId") StateId : RequestBody,
                   @Part("CountryId") CountryId : RequestBody,
                   @Part("PinCode") PinCode : RequestBody,
                   @Part("BankAccountNumber") BankAccountNumber : RequestBody,
                   @Part("IFSCCode") IFSCCode : RequestBody,
                   @Part("TypeOfBankAccount") IsTypeOfBankAccountSalary : RequestBody,
                   @Part("NameOfYourBank") NameOfYourBank : RequestBody,
                   @Part("BankAccountTypeFlag") BankAccountTypeFlag : RequestBody
                       ): Call<ResponseBody>

}