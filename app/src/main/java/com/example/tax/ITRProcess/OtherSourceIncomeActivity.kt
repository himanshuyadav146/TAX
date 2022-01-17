package com.example.tax.ITRProcess

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.tax.ApiCall.APIClient
import com.example.tax.Interfaces.API_Interface
import com.example.tax.R
import com.example.tax.base.BaseActivity
import com.example.tax.models.ApiLogin
import com.example.tax.models.ArrayBaseModel
import com.example.tax.models.Data
import com.example.tax.models.ItrBaseModel
import com.example.tax.utils.toast
import com.google.gson.Gson
import dell.com.allindiaitr.utils.AlertDialogueManager
import dell.com.allindiaitr.utils.ConnectionDetector
import kotlinx.android.synthetic.main.activity_other_source_income.*
import kotlinx.android.synthetic.main.activity_persional_information.cont_button
import retrofit2.Call
import retrofit2.Response

class OtherSourceIncomeActivity : BaseActivity() {
    var objApiLogin = ApiLogin.instance
    lateinit var apI_Interface: API_Interface
    var mContext: Context = this
    var itrBaseModel = ItrBaseModel.instance
    lateinit var baseItrID:String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        apI_Interface = APIClient().getClient().create(API_Interface::class.java)
        baseItrID = intent.getStringExtra("itrid")
        itrBaseModel.setItrId(baseItrID)
        if (ConnectionDetector().isConnectingToInternet(mContext))
            getOtherSource()
        else
            toast("Please Check Your Internet Connection")

        cont_button.setOnClickListener(View.OnClickListener {
            setDataIntoModel()
            if (ConnectionDetector().isConnectingToInternet(mContext))
                postOtherSource()
            else
                toast("Please Check Your Internet Connection")
        })
    }

    override fun getToolbarTitle(): String? {
        return "Other Source"
    }

    override fun getTagName(): String? {
        return "ITR"
    }

    override fun getLayoutResource(): Int {
        return R.layout.activity_other_source_income
    }


    private fun setDataIntoModel() {
        itrBaseModel.setInterestinc(interestIncomeField.text.toString())
        itrBaseModel.setInterestonrd(interestOnRdFdField.text.toString())
        itrBaseModel.setOtherinc(anyOtherIncomeField.text.toString())
    }

    private fun postOtherSource() {
        var dialog = AlertDialogueManager(mContext, "Please Wait")
        val call = apI_Interface.postOtherSource(itrBaseModel)
        Log.d("TEMP_TAG", "url: " + call.request().url.toString());
        call.enqueue(object : retrofit2.Callback<ItrBaseModel> {
            override fun onFailure(call: Call<ItrBaseModel>, t: Throwable) {
                dialog.hideDialog()
                toast("Please Try Again")
            }

            override fun onResponse(call: Call<ItrBaseModel>, response: Response<ItrBaseModel>) {
                if (response.isSuccessful) {
                    dialog.hideDialog()
                    var gson: Gson = Gson()
                    var jsonObj: String = gson.toJson(response.body())
                    response.body()?.getMessage()?.let { toast(it) }
                    intent = Intent(applicationContext, BankDetailsActivity::class.java)
                    intent.putExtra("itrid",itrBaseModel.getItrId())
                    startActivity(intent)
                }
            }
        })
    }

    private fun getOtherSource() {
        var dialog = AlertDialogueManager(mContext, "Please Wait")
        val call = apI_Interface.getOtherSourceByItrId(itrBaseModel.getItrId().toString())
        Log.d("TEMP_TAG", "url: " + call.request().url.toString());
        call.enqueue(object : retrofit2.Callback<ArrayBaseModel> {
            override fun onFailure(call: Call<ArrayBaseModel>, t: Throwable) {
                dialog.hideDialog()
                toast("Please Try Again")
            }

            override fun onResponse(call: Call<ArrayBaseModel>, response: Response<ArrayBaseModel>) {
                if (response.isSuccessful) {
                    dialog.hideDialog()
                    var gson: Gson = Gson()
                    var jsonObj: String = gson.toJson(response.body())
                    setDataInToField(response.body())
                }
            }

        })
    }

    private fun setDataInToField(body: ArrayBaseModel?) {
        var temp:Data = body?.data?.get(0)!!
        interestIncomeField.setText(temp.interestinc.toString())
        interestOnRdFdField.setText(temp.interestonrd.toString())
        anyOtherIncomeField.setText(temp.otherinc.toString())

    }
}
