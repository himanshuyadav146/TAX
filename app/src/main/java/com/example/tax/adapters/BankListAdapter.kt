package com.example.tax.adapters

import android.content.Context
import android.os.Parcel
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.ViewUtils
import androidx.recyclerview.widget.RecyclerView
import com.example.tax.ITRProcess.ITRListActivity
import com.example.tax.Interfaces.OnBankItemClick
import com.example.tax.R
import com.example.tax.holders.BankListHolder
import com.example.tax.models.BankDetail
import com.example.tax.models.Data
import dell.com.allindiaitr.holders.SourceOfIncomeHolder
import dell.com.allindiaitr.models.ITROption_Model

class BankListAdapter() : RecyclerView.Adapter<BankListHolder>(){
    lateinit var mContext: Context
    lateinit var bankList: List<Data>
    lateinit var bankItemClick: OnBankItemClick
//    constructor(parcel: Parcel) : this() {
//
//    }

    constructor(mContext: Context,bankList: List<Data>,bankItemClick: OnBankItemClick) : this() {
        this.mContext = mContext
        this.bankList= bankList
        this.bankItemClick = bankItemClick
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BankListHolder {
        return BankListHolder(LayoutInflater.from(mContext).inflate(R.layout.card_bank_details_list, parent, false))
    }

    override fun onBindViewHolder(holder: BankListHolder, position: Int) {
        holder.tvBankName.text=bankList.get(position).bankname
        holder.tvAcNo.text=bankList.get(position).accountno
//        holder.imgEdit.setOnClickListener(View.OnClickListener {
//            bankItemClick.onClick(bankList.get(position))
//        })

        holder.imgCross.setOnClickListener(View.OnClickListener {
//            bankItemClick.deleteItems(bankList.get(position))
        })
    }

    override fun getItemCount(): Int {
        if (null!=bankList && bankList.size > 0){
            return bankList.size
        }else{
            return 0
        }
    }

}