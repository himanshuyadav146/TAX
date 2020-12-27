package com.example.tax.ITRProcess

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.tax.R
import kotlinx.android.synthetic.main.activity_address_details.*

class AddressDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_address_details)

        cont_button.setOnClickListener(View.OnClickListener {
            intent = Intent(applicationContext, DocumentUploadActivity::class.java)
            startActivity(intent)
        })
    }
}