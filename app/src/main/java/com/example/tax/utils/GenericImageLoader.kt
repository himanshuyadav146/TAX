package com.example.tax.utils

import android.app.Activity
import android.content.Context
import android.text.TextUtils
import android.view.View
import android.widget.ImageView
import androidx.fragment.app.FragmentActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.request.RequestOptions
import com.example.tax.R
import java.lang.Exception

public class GenericImageLoader {
    public fun loadImage(context: Context?, imageView: View?, url: String) {
        context?.let {
            Glide.with(it)
                .load(url) //3
                .centerCrop()
                .placeholder(R.drawable.ic_upload_image) //5
                .error(R.drawable.ic_baseline_error_24) //6
                .fallback(R.drawable.ic_add) //7
                .into(imageView as ImageView)
        }
    }

}