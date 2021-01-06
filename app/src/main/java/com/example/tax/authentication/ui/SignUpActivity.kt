package com.example.tax.authentication.ui

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.tax.ApiCall.APIClient
import com.example.tax.DashBoard.DashBoardActivity
import com.example.tax.Interfaces.API_Interface
import com.example.tax.R
import com.example.tax.models.ApiLogin
import com.example.tax.models.LoginModel
import com.example.tax.models.RegistrationModel
import com.example.tax.utils.AppPreferences
import com.example.tax.utils.toast
import com.google.gson.Gson
import dell.com.allindiaitr.utils.AlertDialogueManager
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_sign_up.*
import kotlinx.android.synthetic.main.activity_sign_up.txt_email
import kotlinx.android.synthetic.main.activity_sign_up.txt_pwd
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignUpActivity : AppCompatActivity() {
    var objApiLogin = ApiLogin.instance
    lateinit var apI_Interface: API_Interface
    lateinit var mContext: Context
    lateinit var appPreferences: AppPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        mContext = this
        apI_Interface = APIClient().getClient().create(API_Interface::class.java)
        appPreferences=AppPreferences(mContext)

        btn_signup.setOnClickListener(View.OnClickListener {
            if(txt_email.text.isNullOrEmpty() && txt_pwd.text.isNullOrEmpty()  && txt_mob.text.isNullOrEmpty()){
                toast("Please Enter Mandatory Fields")
            }else{
                objApiLogin.EmailAddress=txt_email.text.toString()
                objApiLogin.MobileNumber=txt_mob.text.toString()
                objApiLogin.Password=txt_pwd.text.toString()
                objApiLogin.DeviceType="Android"
                postRegistration()
            }
        })

        tv_allready_register.setOnClickListener(View.OnClickListener {
            intent = Intent(applicationContext, LoginActivity::class.java)
            startActivity(intent)
            finish()
        })
    }
    private fun postRegistration() {
        var dialog = AlertDialogueManager(mContext,"Please Wait")
        val call = apI_Interface.postRegistration(objApiLogin)
        call.enqueue(object : Callback<RegistrationModel> {
            override fun onFailure(call: Call<RegistrationModel>, t: Throwable) {
                dialog.hideDialog()
                toast("Please Try Again")
            }
            override fun onResponse(call: Call<RegistrationModel>, response: Response<RegistrationModel>) {
                if(response.isSuccessful){
                    dialog.hideDialog()
                    if(response.body()?.status.equals("200")){
                        toast(response.body()?.message.toString())
                    }
                }
            }
        })
    }
}
