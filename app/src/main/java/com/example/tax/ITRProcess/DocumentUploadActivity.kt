package com.example.tax.ITRProcess

import android.Manifest
import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.media.MediaScannerConnection
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.widget.EditText
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.tax.R
import com.example.tax.models.ApiLogin
import com.example.tax.utils.toast
import kotlinx.android.synthetic.main.activity_document_upload.*
import kotlinx.android.synthetic.main.activity_persional_information.*
import kotlinx.android.synthetic.main.activity_persional_information.cont_button
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.util.*

class DocumentUploadActivity : AppCompatActivity() {

    var GALLERY = 1
    var CAMERA = 2
    var PICK_PDF_CODE = 3
    var PERMISSIONS_REQUEST_CODE = 1024
    var appPermissions = arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.CAMERA)
    var postPath: String? = null
    var password = ""
    var mContext:Context=this
    var mForm16a: Boolean=false;
    var mForm16b: Boolean=false;
    var mAadhar: Boolean=false;
    var mPan: Boolean=false;
    var mOtherDoc: Boolean=false;


    var objApiLogin = ApiLogin.instance
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_document_upload)
        objApiLogin.Test?.let { toast(it) }

        checkAndRequestPermissions()

        cont_button.setOnClickListener(View.OnClickListener {
            intent = Intent(applicationContext, OtherSourceIncomeActivity::class.java)
            startActivity(intent)
        })

//        img_from16_a.setOnClickListener(View.OnClickListener {
//            mForm16a=true
//            uploadDiloag()
//        })
//
//        img_from16_b.setOnClickListener(View.OnClickListener {
//            mForm16b=true
//            uploadDiloag()
//        })
//
//
//        img_aadhar_card.setOnClickListener(View.OnClickListener {
//            mAadhar=true
//            uploadDiloag()
//        })
//
//
//
//        img_pan.setOnClickListener(View.OnClickListener {
//            mPan=true
//            uploadDiloag()
//        })
//
//
//        img_other_doc.setOnClickListener(View.OnClickListener {
//            mOtherDoc=true
//            uploadDiloag()
//        })
    }



    fun uploadDiloag(){
        val items = arrayOf("Select photo from gallery", "Capture photo from camera", "Select PDF of the document")
        AlertDialog.Builder(this)
            .setTitle("Select Action")
            .setItems(items, DialogInterface.OnClickListener { _, which ->
                when (which) {
                    0 -> {
                        val galleryIntent = Intent(Intent.ACTION_PICK,
                            android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                        startActivityForResult(galleryIntent, GALLERY)
                    }
                    1 -> {
                        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                        startActivityForResult(intent, CAMERA)
                    }
                    2 -> {

//                        val intent4 = Intent(this, NormalFilePickActivity::class.java)
//                        intent4.putExtra(Constant.MAX_NUMBER, 9)
//                        intent4.putExtra(IS_NEED_FOLDER_LIST, true)
//                        intent4.putExtra(NormalFilePickActivity.SUFFIX,
//                            arrayOf("pdf"))
//                        startActivityForResult(intent4, PICK_PDF_CODE)
                    }
                }

            }).show()
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)



        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                GALLERY -> {
                    val contentURI = data!!.data
                    try {
                        val bitmap = MediaStore.Images.Media.getBitmap(this.contentResolver, contentURI)
                        postPath = saveImage(bitmap)
                       // uploadConfirm()
                        setFilePathsInToModel(postPath!!)
                    } catch (e: IOException) {
                        e.printStackTrace()
                    }
                }
                CAMERA -> {
                    val thumbnail = data!!.extras!!.get("data") as Bitmap
                    postPath = saveImage(thumbnail)
                    setFilePathsInToModel(postPath!!)
                  //  uploadConfirm()
                }
                PICK_PDF_CODE -> {

//                    val list = data!!.getParcelableArrayListExtra<NormalFile>(Constant.RESULT_PICK_FILE)
//                    for (file in list) {
//                        postPath = file.path
//                    }
//                    uploadConfirm()
                }
            }
        }


    }


    private fun setFilePathsInToModel(path:String){
        if(mForm16a){
            objApiLogin.Form16Url=path
        }
        if(mForm16b){
            objApiLogin.Form16Url2=path
        }
        if(mAadhar){
            objApiLogin.AdhaarUrl=path
        }
        if(mPan){
            objApiLogin.PanUrl=path
        }
        if(mOtherDoc){
            objApiLogin.OtherDocUrl=path
        }

    }

    private fun saveImage(myBitmap: Bitmap):String {
        val bytes = ByteArrayOutputStream()
        myBitmap.compress(Bitmap.CompressFormat.JPEG, 90, bytes)
        val wallpaperDirectory = File((Environment.getExternalStorageDirectory()).toString())
        if (!wallpaperDirectory.exists())
        {
            wallpaperDirectory.mkdirs()
        }
        try
        {
            val f = File(wallpaperDirectory, ((Calendar.getInstance()
                .timeInMillis).toString() + ".jpg"))
            f.createNewFile()
            val fo = FileOutputStream(f)
            fo.write(bytes.toByteArray())
            MediaScannerConnection.scanFile(this,
                arrayOf(f.path),
                arrayOf("image/jpeg"), null)
            fo.close()
            return f.absolutePath
        }
        catch (e1: IOException) {
            e1.printStackTrace()
        }
        return ""
    }


    private fun uploadConfirm(){
        val inflater = mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater.inflate(R.layout.dialog_password, null)
        val alertDialog = AlertDialog.Builder(mContext)
        alertDialog.setView(view)
        alertDialog.setCancelable(false)
       // alertDialog.setIcon(R.drawable.logo)
        alertDialog.setTitle("Password (Optional)")
        var ediTextpassword = view.findViewById<EditText>(R.id.ediTextpassword)
        alertDialog.setPositiveButton("Ok", DialogInterface.OnClickListener { _, _ ->
            password = if (ediTextpassword.text.toString().isEmpty())
                ""
            else
                ediTextpassword.text.toString()
           // uploadFile()
        })
        alertDialog.setNegativeButton("Cancel", DialogInterface.OnClickListener { _, _ ->
            password = ""
           // uploadFile()
        })
        alertDialog.show()
    }



    private fun checkAndRequestPermissions(): Boolean{
        val listPermissionsNeeded = arrayListOf<String>()
        for (i in 0 until appPermissions.size) {
            if (ContextCompat.checkSelfPermission(mContext, appPermissions[i].toString()) != PackageManager.PERMISSION_GRANTED)
                listPermissionsNeeded.add(appPermissions[i].toString())
        }

        if (listPermissionsNeeded.isNotEmpty()){
            ActivityCompat.requestPermissions(this,
                listPermissionsNeeded.toArray(arrayOfNulls(listPermissionsNeeded.size)),
                PERMISSIONS_REQUEST_CODE)
            return false
        }

        return true
    }

}
