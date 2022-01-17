package com.example.tax.holders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.card_itr_list.view.*

class ITRListHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    val tvFirstName=itemView.tvFirstName
    val tvMiddileName=itemView.tvMiddelName
    val tvLastName=itemView.tvLastName
    val tvPAN=itemView.tvPAN
    val tvContinue=itemView.tvContinue
}