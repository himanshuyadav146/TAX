package com.example.tax.ITRProcess

import android.content.Context
import android.content.Intent
import android.graphics.Rect
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import com.example.tax.ApiCall.APIClient
import com.example.tax.Interfaces.API_Interface
import com.example.tax.R
import com.example.tax.base.BaseActivity
import com.example.tax.models.ItrBaseModel
import com.example.tax.utils.AppPreferences
import com.example.tax.utils.LinearSpacingDecoration
import com.example.tax.utils.toast
import com.google.gson.Gson
import dell.com.allindiaitr.adapter.SourceOfIncomeAdapter
import dell.com.allindiaitr.models.ITROption_Model
import dell.com.allindiaitr.utils.AlertDialogueManager
import kotlinx.android.synthetic.main.activity_choose_itrtype.*
import retrofit2.Call
import retrofit2.Response


class ChooseITRTypeActivity : BaseActivity() {

    var itroptions = listOf<String>("Salary/Pension", "House Property", "Business/Profession",
        "Capital Gains", "Other Sources", "Foreign Income")
    var card_img = listOf<Int>(R.drawable.ic_taxes, R.drawable.ic_apartment, R.drawable.ic_taxes,
        R.drawable.ic_taxes, R.drawable.ic_taxes, R.drawable.ic_taxes)
    lateinit var userAssessmentYearId: String
    lateinit var userId: String
    lateinit var apI_Interface : API_Interface
    lateinit var mContext: Context
    private var appPreferences: AppPreferences? = null
    var mModelList = mutableListOf<ITROption_Model>()
    var itrBaseModel = ItrBaseModel.instance

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mContext=this;
        apI_Interface = APIClient().getClient().create(API_Interface::class.java)
        recycler_view_list.setHasFixedSize(true)
        recycler_view_list.layoutManager = GridLayoutManager(mContext, 2) as RecyclerView.LayoutManager?
        recycler_view_list.adapter = SourceOfIncomeAdapter(this, getListData())
//        recycler_view_list.addItemDecoration(LinearSpacingDecoration(itemSpacing = 10, edgeSpacing = 10))
//        recycler_view_list.addItemDecoration(object : ItemDecoration() {
//            fun getItemOffsets(outRect: Rect, view: View?, parent: RecyclerView, state: RecyclerView.State?) {
//                val position = parent.getChildAdapterPosition(view!!) // item position
//                val spanCount = 2
//                val spacing = 10 //spacing between views in grid
//                if (position >= 0) {
//                    val column = position % spanCount // item column
//                    outRect.left =
//                        spacing - column * spacing / spanCount // spacing - column * ((1f / spanCount) * spacing)
//                    outRect.right =
//                        (column + 1) * spacing / spanCount // (column + 1) * ((1f / spanCount) * spacing)
//                    if (position < spanCount) { // top edge
//                        outRect.top = spacing
//                    }
//                    outRect.bottom = spacing // item bottom
//                } else {
//                    outRect.left = 0
//                    outRect.right = 0
//                    outRect.top = 0
//                    outRect.bottom = 0
//                }
//            }
//        })

        cont_button.setOnClickListener(View.OnClickListener {

            intent = Intent(applicationContext, PersionalInformationActivity::class.java)
            startActivity(intent)
            finish()
        })

        getInformation()
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
        for (i in 0 until itroptions.size){
            mModelList.add(ITROption_Model(itroptions[i], card_img[i]))
        }
        return mModelList
    }

    private fun getInformation(){
        itrBaseModel.setType("PD")
        itrBaseModel.setItrId("71387885-5d8a-11eb-8bb6-525400f438a7")
        var dialog = AlertDialogueManager(mContext,"Please Wait")
        val call = apI_Interface.getInformation(itrBaseModel)
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
//                    intent = Intent(applicationContext, AddressDetailsActivity::class.java)
//                    startActivity(intent)
                }
            }

        })
    }
}
