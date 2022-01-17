package com.example.tax.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tax.ITRProcess.ChooseITRTypeActivity
import com.example.tax.R
import com.example.tax.holders.ITRListHolder
import com.example.tax.models.Order
import com.example.tax.utils.Constant

class ITRListAdapter(var mContext: Context, var order: List<Order?>?) : RecyclerView.Adapter<ITRListHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ITRListHolder {
        return ITRListHolder(LayoutInflater.from(mContext).inflate(R.layout.card_itr_list, parent, false))
    }

    override fun onBindViewHolder(holder: ITRListHolder, position: Int) {
        holder.tvFirstName.setText(order?.get(position)?.firstName)
        holder.tvMiddileName.setText(order?.get(position)?.middleName)
        holder.tvLastName.setText(order?.get(position)?.lastName)
        holder.tvPAN.setText(order?.get(position)?.panNumber)
        var itrID=order?.get(position)?.uuid
       holder.tvContinue.setOnClickListener(View.OnClickListener {
           val intent = Intent(mContext, ChooseITRTypeActivity::class.java)
           intent.putExtra(Constant.INTENT_ITR_ID,itrID)
           mContext.startActivity(intent)
       })
    }

    override fun getItemCount(): Int {
        return order?.size!!
    }

//    constructor(mContext: Context)  {
//        this.mContext = mContext
//    }


}