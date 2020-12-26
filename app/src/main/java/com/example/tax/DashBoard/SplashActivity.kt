package com.example.tax.DashBoard

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.tax.R
import com.example.tax.authentication.ui.LoginActivity
import com.example.tax.utils.AppPreferences

class SplashActivity : AppCompatActivity() {

    lateinit var accessTokenString: String
    private var mDelayHandler: Handler? = null
    private val SPLASH_DELAY: Long = 2000
    // private var runOnce: RunOnce? = null
    private var appPreferences: AppPreferences? = null


    internal val mRunnable: Runnable = Runnable {

        if (appPreferences?.userInfo.isNullOrEmpty()) {

            val intent = Intent(applicationContext, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }else{
            val intent = Intent(applicationContext, DashBoardActivity::class.java)
            startActivity(intent)
            finish()
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        mDelayHandler = Handler()
        appPreferences = AppPreferences(this)

        mDelayHandler!!.postDelayed(mRunnable, SPLASH_DELAY)

    }

    public override fun onDestroy() {

        if (mDelayHandler != null) {
            mDelayHandler!!.removeCallbacks(mRunnable)
        }

        super.onDestroy()
    }

}
