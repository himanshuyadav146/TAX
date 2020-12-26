package com.example.tax.ITRProcess

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.tax.R
import com.example.tax.models.ApiLogin
import kotlinx.android.synthetic.main.activity_persional_information.*

class OtherSourceIncomeActivity : AppCompatActivity() {
    var objApiLogin = ApiLogin.instance
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_other_source_income)



        cont_button.setOnClickListener(View.OnClickListener {
            setDataIntoModel()
            intent = Intent(applicationContext, BankDetailsActivity::class.java)
            startActivity(intent)
        })
    }


    private fun setDataIntoModel(){
//        objApiLogin.
    }


}
