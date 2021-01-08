//package com.example.tax.base
//
//import android.content.IntentFilter
//import android.content.pm.ActivityInfo
//import android.os.Build
//import android.os.Bundle
//import android.view.Menu
//import androidx.appcompat.app.AppCompatActivity
//import androidx.appcompat.widget.Toolbar
//import com.example.tax.DashBoard.SplashActivity
//import com.example.tax.MainActivity
//
//
//class BaseActivity: AppCompatActivity() {
//    var menu: Menu? = null
//    var mToolbar: Toolbar? = null
//
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//
//        //SDI-59432
//        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
//            requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
//        }
//        setContentView(getLayoutResource())
////        if (this !is SplashActivity && this !is MainActivity) checkApplicationProperties()
////        LocalBroadcastManager.getInstance(this).registerReceiver(
////            cartUpdatedBroadcastReceiver,
////            IntentFilter(Constants.BROADCAST_CART_UPDATED)
////        )
//        setToolbar()
//    }
//}