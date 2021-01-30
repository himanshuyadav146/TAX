package com.example.tax.base

import android.content.Context
import android.content.pm.ActivityInfo
import android.graphics.drawable.Drawable
import android.os.Build
import android.os.Bundle
import android.text.TextUtils
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import com.example.tax.R
import kotlinx.android.synthetic.main.app_toolbar.*


public abstract class BaseActivity: AppCompatActivity() {
    var menu: Menu? = null
   lateinit var mToolbar: Toolbar


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //SDI-59432
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
            requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        }
        setContentView(getLayoutResource())
//        if (this !is SplashActivity && this !is MainActivity) checkApplicationProperties()
//        LocalBroadcastManager.getInstance(this).registerReceiver(
//            cartUpdatedBroadcastReceiver,
//            IntentFilter(Constants.BROADCAST_CART_UPDATED)
//        )
        setToolbar()
    }


    fun setToolbar() {
//        if (hasToolbar) {
            mToolbar = findViewById(R.id.toolbar)
//            toolbarTitle = findViewById(R.id.toolbar_title)
//            toolbarIcon = findViewById(R.id.toolbar_icon);
            var screenTitle = getToolbarTitle ()
            if (!TextUtils.isEmpty(screenTitle)){
                toolbar_title.setText(screenTitle)
                setSupportActionBar(mToolbar)
               var actionBar = getSupportActionBar ()
                if(actionBar!=null){
                    actionBar!!.setDisplayHomeAsUpEnabled(false)
                    actionBar!!.setHomeButtonEnabled(false)
                    actionBar!!.setDisplayShowTitleEnabled(false)
                }
            }
          var backArrow:Drawable= ContextCompat.getDrawable(this,R.drawable.ic_back_arrow)!!
          mToolbar.setNavigationIcon(backArrow)
          mToolbar.setNavigationContentDescription("")
          mToolbar.setNavigationOnClickListener { v ->onBackPressed()  }
//        mToolbar.setNavigationOnClickListener(View.OnClickListener {
//
//          })

//                toolbarTitle.setText(screenTitle);
//            setSupportActionBar(mToolbar);
//            ActionBar actionBar = getSupportActionBar ();
//            if (actionBar != null) {
//                actionBar.setDisplayHomeAsUpEnabled(false);
//                actionBar.setHomeButtonEnabled(false);
//                actionBar.setDisplayShowTitleEnabled(false);
//            }
//            if (setupAsChild) {
//                if (toolbarIcon != null) {
//                    if (showToolbarIcon) {
//                        GeneralUtils.setMargins(
//                            toolbarIcon,
//                            GeneralUtils.dpToPx(getBaseContext(), 5),
//                            0,
//                            0,
//                            0
//                        );
//                        toolbarIcon.setVisibility(View.VISIBLE);
//                    } else {
//                        toolbarIcon.setVisibility(View.GONE);
//                    }
//                }
//                upArrowDrawable =
//                    backCrossIcon ? ContextCompat.getDrawable(this, R.drawable.ic_cross_white) : isPdpMenu ? ContextCompat.getDrawable(this, R.drawable.ic_back_icon_black) : this instanceof SearchActivity ? ContextCompat.getDrawable(this, R.drawable.ic_arrow_left_white) : ContextCompat.getDrawable(this, R.drawable.ic_back_icon);
//                if (upArrowDrawable != null) {
//                    upArrowDrawable.mutate();
//                }
//                mToolbar.setNavigationIcon(upArrowDrawable);
//                mToolbar.setNavigationContentDescription("");
//                mToolbar.setNavigationOnClickListener(v -> onBackPressed());
//            }
//        }
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onContextItemSelected(item)
    }

    protected abstract fun getToolbarTitle(): String?

    protected abstract fun getTagName(): String?

    protected abstract fun getLayoutResource(): Int


}