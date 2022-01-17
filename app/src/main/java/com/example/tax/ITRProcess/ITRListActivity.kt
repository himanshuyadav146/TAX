package com.example.tax.ITRProcess

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tax.ApiCall.APIClient
import com.example.tax.Interfaces.API_Interface
import com.example.tax.R
import com.example.tax.adapters.ITRListAdapter
import com.example.tax.base.BaseActivity
import com.example.tax.models.ItrBaseModel
import com.example.tax.models.ItrDetailsModel
import com.example.tax.models.LoginModel
import com.example.tax.utils.AppPreferences
import com.example.tax.utils.toast
import com.google.gson.Gson
import dell.com.allindiaitr.utils.AlertDialogueManager
import kotlinx.android.synthetic.main.activity_i_t_r_list.*
import retrofit2.Call
import retrofit2.Response

class ITRListActivity : BaseActivity() {
    lateinit var apI_Interface: API_Interface
    var itrBaseModel = ItrBaseModel.instance
    var mContext: Context = this
    lateinit var appPreferences: AppPreferences
    lateinit var userID:String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        apI_Interface = APIClient().getClient().create(API_Interface::class.java)
        appPreferences=AppPreferences(mContext)
    }

    override fun getToolbarTitle(): String? {
        return "Your ITR"
    }

    override fun getTagName(): String? {
        return "ITR"
    }

    override fun getLayoutResource(): Int {
        return R.layout.activity_i_t_r_list;
    }

}