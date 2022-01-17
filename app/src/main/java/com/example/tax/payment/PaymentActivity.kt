package com.example.tax.payment

import android.app.Activity
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.tax.R
import com.example.tax.base.BaseActivity
import com.example.tax.utils.Constant
import com.razorpay.Checkout
import com.razorpay.PaymentResultListener
import kotlinx.android.synthetic.main.activity_payment.*
import org.json.JSONObject
import java.lang.Exception

class PaymentActivity : BaseActivity(), PaymentResultListener {
    private val REQUEST_CODE_LOCATION=101
    val TAG:String = PaymentActivity::class.toString()
    lateinit var baseItrID:String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
      //  setContentView(R.layout.activity_payment)
        if (intent!=null){
            baseItrID = intent.getStringExtra("itrid")
        }

        Checkout.preload(applicationContext)
        pay_button.setOnClickListener(View.OnClickListener {
            startPayment()
        })

    }


    private fun startPayment() {
        /*
        *  You need to pass current activity in order to let Razorpay create CheckoutActivity
        * */
        val activity:Activity = this
        val co = Checkout()
        co.setKeyID(Constant.TEST_KEY_ID)

        try {
            val options = JSONObject()
            options.put("name","Razorpay Corp")
            options.put("description","Demoing Charges")
            //You can omit the image option to fetch the image from dashboard
            options.put("image","https://s3.amazonaws.com/rzp-mobile/images/rzp.png")
            options.put("theme.color", "#3399cc");
            options.put("currency","INR");
            options.put("order_id", "order_DBJOWzybf0sJbb");
            options.put("amount","100")//pass amount in currency subunits

            val retryObj =  JSONObject()
            retryObj.put("enabled", true);
            retryObj.put("max_count", 4);
            options.put("retry", retryObj);

            val prefill = JSONObject()
            prefill.put("email","gaurav.kumar@example.com")
            prefill.put("contact","9876543210")

            options.put("prefill",prefill)
            co.open(activity,options)
        }catch (e: Exception){
            Toast.makeText(activity,"Error in payment: "+ e.message,Toast.LENGTH_LONG).show()
            e.printStackTrace()
        }
    }


    override fun onPaymentError(errorCode: Int, response: String?) {
        try{
            Toast.makeText(this,"Payment failed $errorCode \n $response", Toast.LENGTH_LONG).show()
        }catch (e: Exception){
            Log.e(TAG,"Exception in onPaymentSuccess", e)
        }
    }

    override fun onPaymentSuccess(razorpayPaymentId: String?) {
        try{
            Toast.makeText(this,"Payment Successful $razorpayPaymentId", Toast.LENGTH_LONG).show()
        }catch (e: Exception){
            Log.e(TAG,"Exception in onPaymentSuccess", e)
        }
    }


    override fun getToolbarTitle(): String? {
        return "Your ITR"
    }

    override fun getTagName(): String? {
        return "ITR"
    }

    override fun getLayoutResource(): Int {
        return R.layout.activity_payment
    }


}