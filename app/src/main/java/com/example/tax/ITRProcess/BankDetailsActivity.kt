package com.example.tax.ITRProcess

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tax.ApiCall.APIClient
import com.example.tax.Interfaces.API_Interface
import com.example.tax.Interfaces.OnBankItemClick
import com.example.tax.R
import com.example.tax.adapters.BankListAdapter
import com.example.tax.base.BaseActivity
import com.example.tax.models.*
import com.example.tax.payment.PaymentActivity
import com.example.tax.utils.AppPreferences
import com.example.tax.utils.Constant
import com.example.tax.utils.toast
import com.google.gson.Gson
import dell.com.allindiaitr.utils.AlertDialogueManager
import dell.com.allindiaitr.utils.ConnectionDetector
import dell.com.allindiaitr.utils.Validation
import kotlinx.android.synthetic.main.activity_bank_details.*
import kotlinx.android.synthetic.main.activity_bank_details.cont_button
import retrofit2.Call
import retrofit2.Response

class BankDetailsActivity : BaseActivity() ,OnBankItemClick {
    var objApiLogin = ApiLogin.instance
    var itrBaseModel = ItrBaseModel.instance
    var mContext: Context = this
//    var data: Data = Data()
    lateinit var apI_Interface: API_Interface
    lateinit var onBankItemClick: OnBankItemClick
    lateinit var appPreferences: AppPreferences
    lateinit var userID:String
    lateinit var bankList:List<Data>
     var accountType= arrayOf<String>("Saving","Current")
    lateinit var baseItrID:String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        apI_Interface = APIClient().getClient().create(API_Interface::class.java)
        onBankItemClick=this
        appPreferences=AppPreferences(mContext)
        baseItrID = intent.getStringExtra("itrid")

        if(appPreferences!=null){
            var gson = Gson()
//            var mMineUserEntity = gson?.fromJson(appPreferences?.userInfo, LoginModel::class.java)
////            userID= mMineUserEntity?.data?.uuid.toString()
//            userID= mMineUserEntity?.data?.id.toString()
        }
        cont_button.setOnClickListener(View.OnClickListener {
            if(bankList.isEmpty()){
                if (Validation().isTextEmpty(bankNameFeild.text.toString(), bankNameFeild)
                    && Validation().isTextEmpty(txtIFSC.text.toString(), txtIFSC)
                    && Validation().isTextEmpty(edit_ac_no.text.toString(), edit_ac_no)){
                    setDataIntoModel()
                    if (ConnectionDetector().isConnectingToInternet(mContext))
                        postBankDetails()
                    else
                        toast("Please Check Your Internet Connection")
                }
            }else{
                val intent=Intent(this,PaymentActivity::class.java)
                intent.putExtra("itrid",baseItrID)
                startActivity(intent)
            }


        })


        if (ConnectionDetector().isConnectingToInternet(mContext)){
            itrBaseModel.setItrId(baseItrID)
            getBankDetailsByItrId()
        }
        else
            toast("Please Check Your Internet Connection")

    }

    override fun getToolbarTitle(): String? {
        return "Bank Details"
    }

    override fun getTagName(): String? {
        return "ITR"
    }

    override fun getLayoutResource(): Int {
        return R.layout.activity_bank_details
    }


    private fun postBankDetails() {
        var dialog = AlertDialogueManager(mContext, "Please Wait")
        val call = apI_Interface.postBankDetails(itrBaseModel)
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
                    //getInformation()
                     intent=Intent(applicationContext,PaymentActivity::class.java)
                    intent.putExtra("itrid",itrBaseModel.getItrId())
                    startActivity(intent)
                }
            }
        })
    }

    private fun getBankDetailsByItrId(){
        var dialog = AlertDialogueManager(mContext, "Please Wait")
        val call = apI_Interface.getBankDetailsByItrid(itrBaseModel.getItrId().toString())
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
                    enabledBankList(response.body())
                }
            }
        })
    }


    private fun setDataIntoModel() {
        itrBaseModel.setBankname(bankNameFeild.text.toString())
        itrBaseModel.setIfsccode(txtIFSC.text.toString())
        itrBaseModel.setAccountno(edit_ac_no.text.toString())
        itrBaseModel.setAccounttype(accountType.get(0))
//        itrBaseModel.userid = userID
        itrBaseModel.userid = "50"
    }

    private fun setDataInToField(body: ArrayBaseModel?) {
        var temp = body?.data?.get(0)
        bankNameFeild.setText(temp?.bankname.toString())
        txtIFSC.setText(temp?.ifsccode.toString())
        edit_ac_no.setText(temp?.accountno.toString())
    }

    private fun enabledBankList(body: ArrayBaseModel?){
        recycle_bank_list.setHasFixedSize(true)
        bankList = body?.data as List<Data>
        recycle_bank_list.layoutManager = LinearLayoutManager(mContext)
        recycle_bank_list.adapter =
            BankListAdapter(this@BankDetailsActivity,
                bankList, onBankItemClick
            )
        llBankDetails.visibility = View.GONE
        recycle_bank_list.visibility=View.VISIBLE
    }




    override fun onClick(bankDetails: BankDetail) {
        bankNameFeild.setText(bankDetails.getBankName().toString())
        txtIFSC.setText(bankDetails.getIfscCode().toString())
        edit_ac_no.setText(bankDetails.getAccountNumber().toString())
        val adapter: ArrayAdapter<*> = ArrayAdapter<Any?>(this, android.R.layout.simple_spinner_item, accountType)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        acc_type_spinner.adapter=adapter
        llBankDetails.visibility=View.VISIBLE
        recycle_bank_list.visibility=View.GONE
       // Toast.makeText(mContext,"Hello",Toast.LENGTH_LONG).show()
    }

    override fun deleteItems(bankDetails: BankDetail) {
        itrBaseModel.setBankDetailId(bankDetails.getUuid().toString())
//        itrBaseModel.setUserId(bankDetails.getUserId())
        deleteBank()
    }

    private fun deleteBank(){
        itrBaseModel.setType(Constant.SCREEN_TYPE_BI)
        var dialog = AlertDialogueManager(mContext, "Please Wait")
        val call = apI_Interface.deleteBank(itrBaseModel)
        Log.d("TEMP_TAG", "url: " + call.request().url.toString());
        call.enqueue(object : retrofit2.Callback<ModelbankList> {
            override fun onFailure(call: Call<ModelbankList>, t: Throwable) {
                dialog.hideDialog()
                toast("Please Try Again")
            }

            override fun onResponse(call: Call<ModelbankList>, response: Response<ModelbankList>) {
                if (response.isSuccessful) {
                    dialog.hideDialog()
                    var gson: Gson = Gson()
                    var jsonObj: String = gson.toJson(response.body())
//                    getInformation()
//                    itrBaseModel.setData(response.body()?.getData())
//
////                    llBankDetails
//                    val bankList:List<BankDetail> = response.body()?.getData()?.getBankDetail() as List<BankDetail>
//                    if(bankList.isEmpty()){
//                        llBankDetails.visibility=View.VISIBLE
//                        recycle_bank_list.visibility=View.GONE
//                    }else{
//                        recycle_bank_list.setHasFixedSize(true)
//                        recycle_bank_list.layoutManager = LinearLayoutManager(mContext)
//                        recycle_bank_list.adapter =
//                            BankListAdapter(this@BankDetailsActivity,
//                                response.body()?.getData()?.getBankDetail() as List<BankDetail>,onBankItemClick
//                            )
//                        llBankDetails.visibility=View.GONE
//                        recycle_bank_list.visibility=View.VISIBLE
//                    }

                }
            }

        })
    }
}
