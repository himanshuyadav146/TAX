package com.example.tax.utils

import android.content.Context
import android.content.SharedPreferences

class AppPreferences(context: Context) {

    private var APP_SHARED_PREFS = "tax.com"
    private var sharedPreferences : SharedPreferences = context.getSharedPreferences(APP_SHARED_PREFS, Context.MODE_PRIVATE)
    private var editor : SharedPreferences.Editor = sharedPreferences.edit()



    fun ClearPreferences(){
        sharedPreferences.edit().clear().commit()
    }

    var accessTokenId: String?
        get() = sharedPreferences.getString("accessTokenId", "")
        set(accessTokenId) {
            editor.putString("accessTokenId", accessTokenId)
            editor.commit()
        }

    var deviceTokenId: String?
        get() = sharedPreferences.getString("deviceTokenId", "")
        set(deviceTokenId) {
            editor.putString("deviceTokenId", deviceTokenId)
            editor.commit()
        }


    var userInfo:String?
    get()=sharedPreferences.getString("userInfo","")
    set(userInfo){
        editor.putString("userInfo", userInfo)
        editor.commit()
    }



}