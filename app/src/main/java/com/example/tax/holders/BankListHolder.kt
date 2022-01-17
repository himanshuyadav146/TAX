package com.example.tax.holders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.card_bank_details_list.view.*

class BankListHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    val tvBankName = itemView.tvBankName
    val tvAcNo = itemView.tvAcNo
    val imgEdit=itemView.imgEdit
    val imgCross=itemView.imgCross
}