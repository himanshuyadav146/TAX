package com.example.tax.ITRProcess

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.airbnb.lottie.utils.Utils
import com.example.tax.ApiCall.APIClient
import com.example.tax.Interfaces.API_Interface
import com.example.tax.R
import com.example.tax.base.BaseActivity
import com.example.tax.models.Data
import com.example.tax.models.ItrBaseModel
import com.example.tax.utils.Constant
import com.example.tax.utils.toast
import com.google.gson.Gson
import dell.com.allindiaitr.utils.AlertDialogueManager
import dell.com.allindiaitr.utils.ConnectionDetector
import dell.com.allindiaitr.utils.Validation
import kotlinx.android.synthetic.main.activity_address_details.*
import kotlinx.android.synthetic.main.activity_address_details.cont_button
import kotlinx.android.synthetic.main.activity_persional_information.*
import retrofit2.Call
import retrofit2.Response

class AddressDetailsActivity : BaseActivity() {
    var itrBaseModel = ItrBaseModel.instance
    var mContext: Context = this
    lateinit var apI_Interface: API_Interface
    lateinit var baseItrID:String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        apI_Interface = APIClient().getClient().create(API_Interface::class.java)
        baseItrID = intent.getStringExtra("itrid")
//        editCountry.setEnabled(false)
        cont_button.setOnClickListener(View.OnClickListener {
            if (Validation().isTextEmpty(editBlockNo.text.toString(), editBlockNo)
                && Validation().isTextEmpty(editBuildingNo.text.toString(), editBuildingNo)
                && Validation().isTextEmpty(editRoad.text.toString(), editRoad)
                && Validation().isTextEmpty(editArea.text.toString(), editArea)
                && Validation().isTextEmpty(editCountry.text.toString(), editCountry)
                && Validation().isTextEmpty(editState.text.toString(), editState)
                && Validation().isCityValid(editCity.text.toString(), editCity)
                && Validation().isPinValid(editPin.text.toString(), editPin)
            ) {
                setDataIntoModel()
                if (ConnectionDetector().isConnectingToInternet(mContext)){
                    postAddressDetails()
                }
                else
                    toast("Please Check Your Internet Connection")
            }

        })

        if (ConnectionDetector().isConnectingToInternet(mContext)){
            itrBaseModel.setItrId(baseItrID)
            getAddressByItrid()
        }
        else
            toast("Please Check Your Internet Connection")
    }

    override fun getToolbarTitle(): String? {
        return "Address Information"
    }

    override fun getTagName(): String? {
        return "ITR"
    }

    override fun getLayoutResource(): Int {
        return R.layout.activity_address_details
    }


    private fun postAddressDetails() {
        var dialog = AlertDialogueManager(mContext, "Please Wait")
        val call = apI_Interface.postAddressInfo(itrBaseModel)
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
                    itrBaseModel = response.body()!!
                    itrBaseModel.setItrId(response.body()?.id.toString())
                    intent = Intent(applicationContext, DocumentUploadActivity::class.java)
                    intent.putExtra("itrid",itrBaseModel.getItrId())
                    startActivity(intent)
                }
            }
        })
    }


    private fun getAddressByItrid() {
        var dialog = AlertDialogueManager(mContext, "Please Wait")
        val call = apI_Interface.getAddressByItrid(itrBaseModel.getItrId().toString())
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
//                    itrBaseModel.setData(response.body()?.getData())
                    itrBaseModel = response.body()!!
                    setDataInToField()
                }
            }

        })
    }

    private fun setDataIntoModel() {
        itrBaseModel.setBlockNo(editBlockNo.text.toString())
        itrBaseModel.setBuilding(editBuildingNo.text.toString())
        itrBaseModel.setRoad(editRoad.text.toString())
        itrBaseModel.setLocality(editArea.text.toString())
        itrBaseModel.setCountry(editCountry.text.toString())
        itrBaseModel.setState(editState.text.toString())
        itrBaseModel.setTown(editCity.text.toString())
        itrBaseModel.setPincode(editPin.text.toString())
    }

    private fun setDataInToField() {
        editBlockNo.setText(itrBaseModel.getBlockNo())
        editBuildingNo.setText(itrBaseModel.getBuilding())
        editRoad.setText(itrBaseModel.getRoad())
        editArea.setText(itrBaseModel.getLocality())
        editState.setText(itrBaseModel.getState())
        editCity.setText(itrBaseModel.getTown())
        editPin.setText(itrBaseModel.getPincode())
        editCountry.setText(itrBaseModel.getCountry())
    }

}