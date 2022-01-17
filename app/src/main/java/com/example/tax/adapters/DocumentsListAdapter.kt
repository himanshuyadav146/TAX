package com.example.tax.adapters

import Files
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tax.Interfaces.OnDocItemClick
import com.example.tax.R
import com.example.tax.models.Data
import com.example.tax.utils.GenericImageLoader
import com.example.tax.utils.toast
import kotlinx.android.synthetic.main.card_doc_list.view.*

class DocumentsListAdapter(var mContext: Context, var docList:List<Files>, var onDocItemClick: OnDocItemClick):RecyclerView.Adapter<DocumentsListAdapter.MyViewHolder>() {



    inner class MyViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val docImage=itemView.imgDoc
        val deleteDoc=itemView.btnDelete
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(mContext).inflate(R.layout.card_doc_list, parent, false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        GenericImageLoader().loadImage(mContext,holder.docImage,docList.get(position).url)
        holder.deleteDoc.setOnClickListener {
            onDocItemClick.deleteDoc(docList.get(position))
        }
    }

    override fun getItemCount(): Int {
        return docList.size
    }

}