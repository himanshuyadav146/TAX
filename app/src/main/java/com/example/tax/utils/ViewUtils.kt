package com.example.tax.utils

import android.content.Context
import android.content.Intent
import android.widget.Toast

fun Context.toast(message:String){
    Toast.makeText(this,message, Toast.LENGTH_LONG).show()
}

fun Context.navigate(mContext:Context,intent:Intent){

}