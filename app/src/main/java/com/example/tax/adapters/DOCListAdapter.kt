package com.example.tax.adapters

import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tax.R
import com.example.tax.holders.DocListHolder
import com.example.tax.models.File

class DOCListAdapter(var mContext: Context, var docList: List<File?>) : RecyclerView.Adapter<DocListHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DocListHolder {
        return DocListHolder(LayoutInflater.from(mContext).inflate(R.layout.card_doc_list, parent, false))
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onBindViewHolder(holder: DocListHolder, position: Int) {
        if(!docList.get(position)?.filePath?.contains(".pdf")!!){
            var url="http://sh017.hostgator.tempwebhost.net/~easytxi6/"+docList.get(position)?.filePath
            Glide.with(mContext).load(url).into(holder.docImage)
        }
    }

    override fun getItemCount(): Int {
        return docList.size
//        return 5
    }


}