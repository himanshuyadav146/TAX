package com.example.tax.DashBoard

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.tax.ITRProcess.ChooseITRTypeActivity
import com.example.tax.ITRProcess.ITRListActivity
import com.example.tax.R
import com.example.tax.authentication.ui.SignUpActivity
import com.example.tax.utils.toast
import kotlinx.android.synthetic.main.activity_dash_board.*
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.app_toolbar.*

class DashBoardActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dash_board)
       // toolbar_title.text = "Choose Service"
        card_itr.setOnClickListener(View.OnClickListener {
            intent = Intent(applicationContext, ChooseITRTypeActivity::class.java)
//            intent = Intent(applicationContext, ITRListActivity::class.java)
            startActivity(intent)

        })
    }
}
