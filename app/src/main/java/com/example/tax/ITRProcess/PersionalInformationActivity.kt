package com.example.tax.ITRProcess

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.tax.R
import com.example.tax.authentication.ui.SignUpActivity
import com.example.tax.models.ApiLogin
import kotlinx.android.synthetic.main.activity_persional_information.*

class PersionalInformationActivity : AppCompatActivity() {

    var objApiLogin = ApiLogin.instance

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_persional_information)
        cont_button.setOnClickListener(View.OnClickListener {

          //  setDataIntoModel()
            intent = Intent(applicationContext, DocumentUploadActivity::class.java)
           // objApiLogin.Test="himanshu new test"
            startActivity(intent)
        })

        
    }

//    private fun setDataIntoModel(){
//        objApiLogin.firstName= firstNameField.text.toString()
//        objApiLogin.lastName=lastNameField.text.toString()
//        objApiLogin.mobileNo=mobileNumberField.text.toString()
//        objApiLogin.email=emailField.text.toString()
//        objApiLogin.PanNumber=panNumberField.text.toString()
//        objApiLogin.DateOfBirth=dobField.text.toString()
//
//
//    }
}
