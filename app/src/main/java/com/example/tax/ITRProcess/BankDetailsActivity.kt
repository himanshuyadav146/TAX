package com.example.tax.ITRProcess

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.tax.ApiCall.APIClient
import com.example.tax.Interfaces.API_Interface
import com.example.tax.R
import com.example.tax.models.ApiLogin
import dell.com.allindiaitr.utils.AlertDialogueManager
import kotlinx.android.synthetic.main.activity_bank_details.*
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File
import java.lang.Exception

class BankDetailsActivity : AppCompatActivity() {

    var objApiLogin = ApiLogin.instance
    var mContext:Context=this
    lateinit var apI_Interface: API_Interface


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bank_details)
        apI_Interface = APIClient().getClient().create(API_Interface::class.java)

        calculate_button.setOnClickListener(View.OnClickListener {
            setDataIntoModel();
        })


    }


    private fun setDataIntoModel(){
        objApiLogin.NameOfYourBank=bankNameFeild.text.toString()
        objApiLogin.BankAccountNumber=accNumberField.text.toString()
        objApiLogin.IFSCCode=ifscCodeField.text.toString()

        postData()
    }


    private fun postData(){
        var dialog = AlertDialogueManager(mContext,"Please Wait")

        val file = File(objApiLogin.Form16Url!!)
        val mFile = RequestBody.create("*/*".toMediaTypeOrNull(), file)
        val Form16UrlBody = MultipartBody.Part.createFormData("file", file.name, mFile)



        val file1 = File(objApiLogin.Form16Url2!!)
        val mFile1 = RequestBody.create("*/*".toMediaTypeOrNull(), file1)
        val Form16Url2Body = MultipartBody.Part.createFormData("file", file.name, mFile1)



        val file2 = File(objApiLogin.PanUrl!!)
        val mFile2 = RequestBody.create("*/*".toMediaTypeOrNull(), file2)
        val PanUrlBody = MultipartBody.Part.createFormData("file", file.name, mFile2)




        val file3 = File(objApiLogin.AdhaarUrl!!)
        val mFile3 = RequestBody.create("*/*".toMediaTypeOrNull(), file3)
        val AdhaarUrlBody = MultipartBody.Part.createFormData("file", file.name, mFile3)


        val file4 = File(objApiLogin.OtherDocUrl!!)
        val mFile4 = RequestBody.create("*/*".toMediaTypeOrNull(), file4)
        val OtherDocUrlBody = MultipartBody.Part.createFormData("file", file.name, mFile4)

        val call=apI_Interface.postData(Form16UrlBody,Form16Url2Body,PanUrlBody,AdhaarUrlBody,OtherDocUrlBody,
//            RequestBody.create("text/plain".toMediaTypeOrNull(), objApiLogin.userId.toString()),
            RequestBody.create("text/plain".toMediaTypeOrNull(), "ITRC01"),
            RequestBody.create("text/plain".toMediaTypeOrNull(), "Gaurav"),
            RequestBody.create("text/plain".toMediaTypeOrNull(), "ratan"),
            RequestBody.create("text/plain".toMediaTypeOrNull(), "Srivastava"),
            RequestBody.create("text/plain".toMediaTypeOrNull(), "M"),
            RequestBody.create("text/plain".toMediaTypeOrNull(), "11/08/1989"),
            RequestBody.create("text/plain".toMediaTypeOrNull(), "CABPS0051H"),
            RequestBody.create("text/plain".toMediaTypeOrNull(), "Neel Rata Srievastava"),
            RequestBody.create("text/plain".toMediaTypeOrNull(), "Gauravratan@gmail.com"),
            RequestBody.create("text/plain".toMediaTypeOrNull(), "9716360465"),
            RequestBody.create("text/plain".toMediaTypeOrNull(), "1111222233334444"),
            RequestBody.create("text/plain".toMediaTypeOrNull(), "Doc"),
            RequestBody.create("text/plain".toMediaTypeOrNull(), "1"),
            RequestBody.create("text/plain".toMediaTypeOrNull(), "0"),
            RequestBody.create("text/plain".toMediaTypeOrNull(), "0"),
            RequestBody.create("text/plain".toMediaTypeOrNull(), "0"),
            RequestBody.create("text/plain".toMediaTypeOrNull(), "0"),
            RequestBody.create("text/plain".toMediaTypeOrNull(), "0"),
            RequestBody.create("text/plain".toMediaTypeOrNull(), "150"),
            RequestBody.create("text/plain".toMediaTypeOrNull(), "qweqweqw"),
            RequestBody.create("text/plain".toMediaTypeOrNull(), "qweqwe"),

            RequestBody.create("text/plain".toMediaTypeOrNull(), "vns"),
            RequestBody.create("text/plain".toMediaTypeOrNull(), "1"),
            RequestBody.create("text/plain".toMediaTypeOrNull(), "ffff"),
            RequestBody.create("text/plain".toMediaTypeOrNull(), "22222"),
            RequestBody.create("text/plain".toMediaTypeOrNull(), objApiLogin.BankAccountNumber.toString()),
            RequestBody.create("text/plain".toMediaTypeOrNull(), objApiLogin.IFSCCode.toString()),
            RequestBody.create("text/plain".toMediaTypeOrNull(), "0"),
            RequestBody.create("text/plain".toMediaTypeOrNull(), objApiLogin.NameOfYourBank.toString()),
            RequestBody.create("text/plain".toMediaTypeOrNull(), "SAving")
        )

        call.enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                try {
                    if (response.isSuccessful) {
                        if (response.body() != null) {
                            dialog.hideDialog()
//                            if (ConnectionDetector().isConnectingToInternet(mContext)){
//                                getDocumentList()
//                            }
//                            else {
//                                Toast.makeText(mContext, "Please Check Your Internet Connection", Toast.LENGTH_SHORT).show()
//                            }
                        }
                        else {
                            dialog.hideDialog()
                        }
                    } else {
                        dialog.hideDialog()
                        Toast.makeText(applicationContext, "problem uploading file", Toast.LENGTH_SHORT).show()
                    }
                }
                catch (e: Exception) {
                    e.printStackTrace()
                    dialog.hideDialog()
                    Toast.makeText(mContext, resources.getString(R.string.error_message), Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                dialog.hideDialog()
                Toast.makeText(mContext, resources.getString(R.string.error_message), Toast.LENGTH_SHORT).show()
            }
        })


    }



}
