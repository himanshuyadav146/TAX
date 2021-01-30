package com.example.tax.ITRProcess

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import com.example.tax.ApiCall.APIClient
import com.example.tax.DashBoard.DashBoardActivity
import com.example.tax.Interfaces.API_Interface
import com.example.tax.R
import com.example.tax.authentication.ui.SignUpActivity
import com.example.tax.base.BaseActivity
import com.example.tax.models.ApiLogin
import com.example.tax.models.Data
import com.example.tax.models.ItrBaseModel
import com.example.tax.models.LoginModel
import com.example.tax.utils.AppPreferences
import com.example.tax.utils.toast
import com.google.gson.Gson
import dell.com.allindiaitr.utils.AlertDialogueManager
import dell.com.allindiaitr.utils.ConnectionDetector
import dell.com.allindiaitr.utils.Validation
import kotlinx.android.synthetic.main.activity_persional_information.*
import okhttp3.Callback
import okhttp3.ResponseBody
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
            var mMineUserEntity = gson?.fromJson(appPreferences?.userInfo, LoginModel::class.java)
            itrBaseModel.setUserId(mMineUserEntity.data?.uuid)
//            itrBaseModel.setItrId("")
           // itrBaseModel.setItrId(mMineUserEntity.data?.uuid)
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

        setDataInToField()
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
        itrBaseModel.setType("PD");
        itrBaseModel.setFinancialYear("2020-2022")
        itrBaseModel.setFirstName(editFName.text.toString())
        itrBaseModel.setMiddleName(editMName.text.toString())
        itrBaseModel.setLastName(editLName.text.toString())
        itrBaseModel.setEmail(editEmail.text.toString())
        itrBaseModel.setAadharCardNumber(editAadhar.text.toString())
        itrBaseModel.setMobileNumber(editMobile.text.toString())
        itrBaseModel.setDOB(editDOB.text.toString())
        itrBaseModel.setPanNumber(editPAN.text.toString())
    }

    private fun setDataInToField(){
        if (itrBaseModel!=null){
            if(itrBaseModel.getData()!=null){
                val data:Data=itrBaseModel.getData()!!
                editFName.setText(data.getFirstName().toString())
                editMName.setText(data.getMiddleName().toString())
                editLName.setText(data.getLastName().toString())
                editEmail.setText(data.getEmail().toString())
                editAadhar.setText(data.getAadharCardNumber().toString())
                editMobile.setText(data.mobileNumber.toString())
                editDOB.setText(data.getDOB().toString())
                editPAN.setText(data.getPanNumber().toString())

            }
        }
    }

    private fun postPersonalInfo(){
        var dialog = AlertDialogueManager(mContext,"Please Wait")
        val call = apI_Interface.postInformation(itrBaseModel)
        Log.d("TEMP_TAG", "url: " + call.request().url.toString());
        call.enqueue(object : retrofit2.Callback<ItrBaseModel> {
            override fun onFailure(call: Call<ItrBaseModel>, t: Throwable) {
                dialog.hideDialog()
                toast("Please Try Again")
            }
            override fun onResponse(call: Call<ItrBaseModel>, response: Response<ItrBaseModel>) {
                if(response.isSuccessful){
                    dialog.hideDialog()
                    var gson: Gson = Gson()
                    var jsonObj:String=gson.toJson(response.body())
                    itrBaseModel.setData(response.body()?.getData())
                    intent = Intent(applicationContext, AddressDetailsActivity::class.java)
                    startActivity(intent)
                }
            }

        })
    }
}
