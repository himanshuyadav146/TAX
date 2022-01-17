package com.example.tax.authentication.ui

import android.app.ActivityOptions
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Pair
import android.view.View
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.tax.ApiCall.APIClient
import com.example.tax.DashBoard.DashBoardActivity
import com.example.tax.Interfaces.API_Interface
import com.example.tax.R
import com.example.tax.models.ApiLogin
import com.example.tax.models.LoginModel
import com.example.tax.models.RegistrationModel
import com.example.tax.utils.AppPreferences
import com.example.tax.utils.Constant
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


    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
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
                var objReg= RegistrationModel()
                objReg.email=txt_email.text.toString()
                objReg.password=txt_pwd.text.toString()
                objReg.platform=Constant.PLATFORM
                postLogin(objReg)
            }

        })

        btRegister.setOnClickListener(View.OnClickListener {
            val a = Intent(this, SignUpActivity::class.java)
//            val pairs: Array<Pair<*, *>?> = arrayOfNulls(1)
//            pairs[0] = Pair<View, String>(tvLogin, "login")
//            val activityOptions = ActivityOptions.makeSceneTransitionAnimation(this)

            val p1 = Pair.create<View, String>(tvLogin, "login")
            val activityOptions=ActivityOptions.makeSceneTransitionAnimation(this, p1)
            startActivity(a, activityOptions.toBundle())
        })

//        tv_not_register.setOnClickListener(View.OnClickListener {
//            intent = Intent(applicationContext, SignUpActivity::class.java)
//            startActivity(intent)
//            finish()
//        })

    }



    private fun postLogin(objReg: RegistrationModel) {

        var dialog = AlertDialogueManager(mContext,"Please Wait")

        val call = apI_Interface.postLogin(objReg)
        call.enqueue(object : Callback<RegistrationModel>{
            override fun onFailure(call: Call<RegistrationModel>, t: Throwable) {
                dialog.hideDialog()
                toast("Please Try Again")
            }
            override fun onResponse(call: Call<RegistrationModel>, response: Response<RegistrationModel>) {
                if(response.isSuccessful){
                    if(response.body()?.status.equals("failed")){
                        dialog.hideDialog()
                        toast("Failed to Login")
                    }else{
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
            }

        })

    }
}



