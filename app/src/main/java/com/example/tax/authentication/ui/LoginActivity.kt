package com.example.tax.authentication.ui

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.ViewUtils
import com.example.tax.ApiCall.APIClient
import com.example.tax.DashBoard.DashBoardActivity
import com.example.tax.Interfaces.API_Interface
import com.example.tax.R
import com.example.tax.models.ApiLogin
import com.example.tax.models.Data
import com.example.tax.models.LoginModel
import com.example.tax.utils.AppPreferences
import com.example.tax.utils.toast
import com.google.gson.Gson
import dell.com.allindiaitr.utils.AlertDialogueManager
import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {

    var objApiLogin = ApiLogin.instance
    lateinit var apI_Interface: API_Interface
    lateinit var mContext: Context
    lateinit var appPreferences: AppPreferences


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        mContext = this
        apI_Interface = APIClient().getClient().create(API_Interface::class.java)
        appPreferences=AppPreferences(mContext)

        btn_login.setOnClickListener(View.OnClickListener {

            if(txt_email.text.isNullOrEmpty() && txt_pwd.text.isNullOrEmpty()){
                toast("Please Enter Mandatory Fields")
            }else{
                objApiLogin.userId=txt_email.text.toString()
                objApiLogin.Password=txt_pwd.text.toString()
//                postLogin()

                intent = Intent(applicationContext, DashBoardActivity::class.java)
                toast("Successfully Login")
                startActivity(intent)
                finish()
            }

        })

        tv_not_register.setOnClickListener(View.OnClickListener {
            intent = Intent(applicationContext, SignUpActivity::class.java)
            startActivity(intent)
            finish()
        })


//        tv_not_register.setOnClickListener { View.OnClickListener{
//            intent = Intent(applicationContext, SignUpActivity::class.java)
//            startActivity(intent)
//        } }
    }



    private fun postLogin() {

        var dialog = AlertDialogueManager(mContext,"Please Wait")

        val call = apI_Interface.postLogin(objApiLogin)
        call.enqueue(object : Callback<LoginModel>{
            override fun onFailure(call: Call<LoginModel>, t: Throwable) {
                dialog.hideDialog()
                toast("Please Try Again")
            }

            override fun onResponse(call: Call<LoginModel>, response: Response<LoginModel>) {
                if(response.isSuccessful){
                    dialog.hideDialog()
                    var gson:Gson= Gson()
                    var jsonObj:String=gson.toJson(response.body())
                    appPreferences.userInfo=jsonObj
                    intent = Intent(applicationContext, DashBoardActivity::class.java)
                    toast("Successfully Login")
                    startActivity(intent)
                    finish()
                }
            }

        })

    }
}



