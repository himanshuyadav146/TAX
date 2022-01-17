package com.example.tax.ITRProcess

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import com.example.tax.ApiCall.APIClient
import com.example.tax.Interfaces.API_Interface
import com.example.tax.R
import com.example.tax.base.BaseActivity
import com.example.tax.models.*
import com.example.tax.utils.AppPreferences
import com.example.tax.utils.Constant
import com.example.tax.utils.toast
import com.google.gson.Gson
import dell.com.allindiaitr.utils.AlertDialogueManager
import dell.com.allindiaitr.utils.ConnectionDetector
import dell.com.allindiaitr.utils.Validation
import kotlinx.android.synthetic.main.activity_persional_information.*
import retrofit2.Call
import retrofit2.Response

class PersionalInformationActivity : BaseActivity() {

    var objApiLogin = ApiLogin.instance
    var itrBaseModel = ItrBaseModel.instance
    var mContext:Context=this
    lateinit var apI_Interface: API_Interface
    lateinit var appPreferences: AppPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        apI_Interface = APIClient().getClient().create(API_Interface::class.java)
        appPreferences=AppPreferences(mContext)
        if (!TextUtils.isEmpty(appPreferences.userInfo)) {
            var gson = Gson()
            var mMineUserEntity = gson?.fromJson(appPreferences?.userInfo, UserInformation::class.java)
//            itrBaseModel.setUserId(mMineUserEntity.id.toString())
            itrBaseModel.userid=mMineUserEntity.id.toString()
        }
        cont_button.setOnClickListener(View.OnClickListener {
            if(Validation().isNameValid(editFName.text.toString(),editFName,"Please Enter First Name")
                && Validation().isNameValid(editLName.text.toString(),editLName,"Please Enter Last Name")
                && Validation().isMobileValid(editMobile.text.toString(),editMobile,"Enter your mobile number")
                && Validation().isDobValid(editDOB.text.toString(),editDOB,"Select the date")
                && Validation().isEmailValid(editEmail.text.toString(),editEmail,"Enter your valid email id")
                && Validation().isPanValid(editPAN.text.toString(),editPAN,"Enter your pan number")
                && Validation().isAadhaarValid(editAadhar.text.toString(),editAadhar, "Enter your Aadhaar card")
            ){
                setDataIntoModel()
                if (ConnectionDetector().isConnectingToInternet(mContext))
                postPersonalInfo()
                else
                 toast("Please Check Your Internet Connection")
            }
        })

//        setDataInToField()
        getPersonalInfo("41")
    }

    override fun getToolbarTitle(): String? {
        return "Personal Information"
    }

    override fun getTagName(): String? {
        return "ITR"
    }

    override fun getLayoutResource(): Int {
        return R.layout.activity_persional_information
    }

    private fun setDataIntoModel(){
        itrBaseModel.financialyear="2020-2021"
        itrBaseModel.firstname=editFName.text.toString()
        itrBaseModel.lastname=editLName.text.toString()
        itrBaseModel.mobileno=editMobile.text.toString()
        itrBaseModel.dob=editDOB.text.toString()
        itrBaseModel.emailid=editEmail.text.toString()
        itrBaseModel.panno=editPAN.text.toString()
        itrBaseModel.aadharno=editAadhar.text.toString()
      //  itrBaseModel.userid="123"
        itrBaseModel.sourceofincome="sallary"
    }

    private fun setDataInToField(body: ItrBaseModel?) {
        if (itrBaseModel!=null){
                editFName.setText(itrBaseModel.firstname)
                editMName.setText(itrBaseModel.getMiddleName().toString())
                editLName.setText(itrBaseModel.lastname)
                editEmail.setText(itrBaseModel.emailid)
                editAadhar.setText(itrBaseModel.aadharno)
                editMobile.setText(itrBaseModel.mobileno)
                editDOB.setText(itrBaseModel.dob)
                editPAN.setText(itrBaseModel.panno)

        }
    }

    private fun postPersonalInfo(){
        var dialog = AlertDialogueManager(mContext,Constant.PLEASE_WAIT)
        val call = apI_Interface.postPreInformation(itrBaseModel)
        Log.d("TEMP_TAG", "url: " + call.request().url.toString());
        call.enqueue(object : retrofit2.Callback<ItrBaseModel> {
            override fun onFailure(call: Call<ItrBaseModel>, t: Throwable) {
                dialog.hideDialog()
                toast(Constant.PLEASE_TRY_AGAIN)
            }
            override fun onResponse(call: Call<ItrBaseModel>, response: Response<ItrBaseModel>) {
                dialog.hideDialog()
                if(response.isSuccessful && !response.body()?.status.equals("failed")){
                    var gson: Gson = Gson()
                    var jsonObj:String=gson.toJson(response.body())
                    itrBaseModel= response.body()!!
                    itrBaseModel.setItrId(response.body()?.id.toString())
                    intent = Intent(applicationContext, AddressDetailsActivity::class.java)
                    intent.putExtra("itrid",itrBaseModel.getItrId())
                    startActivity(intent)
                }else{
                    toast("Something went wrong!")
                }
            }

        })
    }


    private fun getPersonalInfo(id:String){
        var dialog = AlertDialogueManager(mContext,Constant.PLEASE_WAIT)
        val call = apI_Interface.getPersonlinfo(id)
        call.enqueue(object : retrofit2.Callback<ItrBaseModel> {
            override fun onFailure(call: Call<ItrBaseModel>, t: Throwable) {
                dialog.hideDialog()
                toast(Constant.PLEASE_TRY_AGAIN)
            }
            override fun onResponse(call: Call<ItrBaseModel>, response: Response<ItrBaseModel>) {
                dialog.hideDialog()
                if(response.isSuccessful && !response.body()?.status.equals("failed")){
                    var gson: Gson = Gson()
                    var jsonObj:String=gson.toJson(response.body())
                    itrBaseModel = response.body()!!
                    itrBaseModel.setItrId(response.body()?.id.toString())
                    setDataInToField(itrBaseModel)
                }else{
                    toast("Something went wrong!")
                }
            }

        })
    }
}
