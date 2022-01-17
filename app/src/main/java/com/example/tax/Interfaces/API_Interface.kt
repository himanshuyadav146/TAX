package com.example.tax.Interfaces

import com.example.tax.models.*
import com.google.android.gms.common.internal.safeparcel.SafeParcelable
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

interface API_Interface {

 //   http://sh017.hostgator.tempwebhost.net/~easytxi6/api/v1/login
//    @POST("api/v1/login")
//    fun postLogin(@Body objApiLogin : ApiLogin): Call<LoginModel>

//    @POST("api/v1/registration")
//    fun postRegistration(@Body objApiLogin : ApiLogin): Call<RegistrationModel>

    @POST("api/v1/create-order")
    fun postInformation(@Body itrBase:ItrBaseModel):Call<ItrBaseModel>

//    @POST("api/v1/create-order")
//    fun postInformation(@Body data:Data):Call<ItrBaseModel>

//    @POST("api/v1/get-detials")
//    fun getInformation(@Body data:ItrBaseModel):Call<ItrBaseModel>

//    @Multipart
//    @POST("api/v1/document-upload")
//    fun uploadFile(@Part File: MultipartBody.Part,
//                         @Part("ItrId") ItrId : RequestBody,
//                         @Part("Type") Type : RequestBody,
////                         @Part("UserAssessmentYearId") userAssessmentYearId : RequestBody,
//                         @Part("Password") Password : RequestBody): Call<ResponseBody>



    @POST("api/v1/get-order")
    fun getITRList(@Body  data:ItrBaseModel):Call<ItrDetailsModel>


    @POST("api/v1/get-document")
    fun getDocumentList(@Body  data:ItrBaseModel):Call<ItrBaseModel>

    @POST("api/v1/delete-bank")
    fun deleteBank(@Body  data:ItrBaseModel):Call<ModelbankList>



    //////////////////////// New Implementation ///////////////////////////////

    @POST("api/addjsonuser")
    fun postRegistration(@Body objReg: RegistrationModel): Call<RegistrationModel>

    @POST("api/sanctum/json/token")
    fun postLogin(@Body objReg: RegistrationModel): Call<RegistrationModel>

    @POST("api/addpersonlinfo")
    fun postPreInformation(@Body itrBase:ItrBaseModel):Call<ItrBaseModel>

    @POST("api/addupdateaddressinfobyitrid")
    fun postAddressInfo(@Body itrBase:ItrBaseModel):Call<ItrBaseModel>

    @POST("api/addupdateothersource")
    fun postOtherSource(@Body itrBase:ItrBaseModel):Call<ItrBaseModel>

    @POST("api/bankdetails")
    fun postBankDetails(@Body itrBase:ItrBaseModel):Call<ItrBaseModel>

    @Multipart
    @POST("api/sendfile")
    fun uploadFile(@Part File: MultipartBody.Part,
                   @Part("itrid") ItrId : RequestBody,
                   @Part("documenttype") Type : RequestBody,
                   @Part("password") Password : RequestBody): Call<ResponseBody>


    @GET("api/getpersonalinfobyid/{id}")
    fun getPersonlinfo(@Path("id") id:String):Call<ItrBaseModel>

    @GET("api/getaddressbyitrid/{itrid}")
    fun getAddressByItrid(@Path("itrid") itrid:String):Call<ItrBaseModel>

    @GET("api/getothersourcebyitrid/{itrid}")
    fun getOtherSourceByItrId(@Path("itrid") itrid:String):Call<ArrayBaseModel>

    @GET("api/getbankdetailsbyitrid/{itrid}")
    fun getBankDetailsByItrid(@Path("itrid") itrid:String):Call<ArrayBaseModel>

    @GET("api/getfilesbyitrid/{itrid}")
    fun getDocumentsByItrid(@Path("itrid") itrid:String):Call<DocumentsModel>

    @GET("api/deletefilesbyid/{itrid}")
    fun deleteDocumentsById(@Path("itrid") itrid:String):Call<DocumentsModel>


    //////////////////////// New Implementation ///////////////////////////////////

}