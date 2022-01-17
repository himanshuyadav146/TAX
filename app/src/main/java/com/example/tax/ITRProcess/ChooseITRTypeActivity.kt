package com.example.tax.ITRProcess

import android.content.ContentUris
import android.content.Context
import android.content.Intent
import android.database.Cursor
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.DocumentsContract
import android.provider.MediaStore
import android.text.TextUtils
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tax.ApiCall.APIClient
import com.example.tax.Interfaces.API_Interface
import com.example.tax.R
import com.example.tax.base.BaseActivity
import com.example.tax.models.ItrBaseModel
import com.example.tax.utils.AppPreferences
import com.example.tax.utils.Constant
import com.example.tax.utils.toast
import com.google.gson.Gson
import dell.com.allindiaitr.adapter.SourceOfIncomeAdapter
import dell.com.allindiaitr.models.ITROption_Model
import dell.com.allindiaitr.utils.AlertDialogueManager
import kotlinx.android.synthetic.main.activity_choose_itrtype.*
import retrofit2.Call
import retrofit2.Response
import java.util.*


class ChooseITRTypeActivity : BaseActivity() {

    var itroptions = listOf<String>(
        "Salary/Pension", "House Property", "Business/Profession",
        "Capital Gains", "Other Sources", "Foreign Income"
    )
    var card_img = listOf<Int>(
        R.drawable.ic_taxes, R.drawable.ic_apartment, R.drawable.ic_taxes,
        R.drawable.ic_taxes, R.drawable.ic_taxes, R.drawable.ic_taxes
    )
    lateinit var userAssessmentYearId: String
    lateinit var userId: String
    lateinit var apI_Interface: API_Interface
    lateinit var mContext: Context
    private var appPreferences: AppPreferences? = null
    var mModelList = mutableListOf<ITROption_Model>()
    var itrBaseModel = ItrBaseModel.instance
    lateinit var sourceOfIncomeAdapter: SourceOfIncomeAdapter
    lateinit var itrID: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mContext = this;
        apI_Interface = APIClient().getClient().create(API_Interface::class.java)
        val intentITRID = intent
//        if (intentITRID != null) {
//            itrID = intentITRID.getStringExtra(Constant.INTENT_ITR_ID)
//        }
        recycler_view_list.setHasFixedSize(true)
        recycler_view_list.layoutManager =
            GridLayoutManager(mContext, 2) as RecyclerView.LayoutManager?
//        recycler_view_list.adapter = SourceOfIncomeAdapter(this, getListData())
        sourceOfIncomeAdapter = SourceOfIncomeAdapter(this, getListData())
        recycler_view_list.adapter = sourceOfIncomeAdapter


        cont_button.setOnClickListener(View.OnClickListener {

            intent = Intent(applicationContext, PersionalInformationActivity::class.java)
            startActivity(intent)
            finish()
        })


    }

    override fun getToolbarTitle(): String? {
        return "Choose ITR Type"
    }

    override fun getTagName(): String? {
        return "ITR"
    }

    override fun getLayoutResource(): Int {
        return R.layout.activity_choose_itrtype
    }

    private fun getListData(): List<ITROption_Model> {
        mModelList.clear()
        for (i in 0 until itroptions.size) {
            mModelList.add(ITROption_Model(itroptions[i], card_img[i]))
        }
        return mModelList
    }

}
