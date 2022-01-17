package com.example.tax.ITRProcess

import Files
import android.Manifest
import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.media.MediaScannerConnection
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tax.ApiCall.APIClient
import com.example.tax.Interfaces.API_Interface
import com.example.tax.Interfaces.OnDocItemClick
import com.example.tax.R
import com.example.tax.adapters.DOCListAdapter
import com.example.tax.adapters.DocumentsListAdapter
import com.example.tax.base.BaseActivity
import com.example.tax.models.ApiLogin
import com.example.tax.models.DocumentsModel
import com.example.tax.models.ItrBaseModel
import com.example.tax.utils.toast
import com.google.gson.Gson
import com.vincent.filepicker.Constant
import com.vincent.filepicker.activity.BaseActivity.IS_NEED_FOLDER_LIST
import com.vincent.filepicker.activity.NormalFilePickActivity
import com.vincent.filepicker.filter.entity.NormalFile
import dell.com.allindiaitr.utils.AlertDialogueManager
import dell.com.allindiaitr.utils.ConnectionDetector
import droidninja.filepicker.utils.ContentUriUtils
import kotlinx.android.synthetic.main.activity_persional_information.cont_button
import kotlinx.android.synthetic.main.card_doc_list.*
import kotlinx.android.synthetic.main.card_upload_image.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.*
import java.util.*


class DocumentUploadActivity : BaseActivity(), OnDocItemClick {
    var GALLERY = 1
    var CAMERA = 2
    var PICK_PDF_CODE = 3
    var PERMISSIONS_REQUEST_CODE = 1024
    var appPermissions = arrayOf(
        Manifest.permission.WRITE_EXTERNAL_STORAGE,
        Manifest.permission.READ_EXTERNAL_STORAGE,
        Manifest.permission.CAMERA
    )
    var postPath: String? = null
    var password = ""
    var mContext: Context = this
    var mForm16a: Boolean = false;
    var mForm16b: Boolean = false;
    var mAadhar: Boolean = false;
    var mPan: Boolean = false;
    var mOtherDoc: Boolean = false;
    lateinit var apI_Interface: API_Interface
    var objApiLogin = ApiLogin.instance
    var itrBaseModel = ItrBaseModel.instance
    lateinit var baseItrID: String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_document_upload)
        objApiLogin.Test?.let { toast(it) }
        apI_Interface = APIClient().getClient().create(API_Interface::class.java)
        baseItrID = intent.getStringExtra("itrid")
        checkAndRequestPermissions()

        cont_button.setOnClickListener(View.OnClickListener {
            intent = Intent(applicationContext, OtherSourceIncomeActivity::class.java)
            intent.putExtra("itrid", itrBaseModel.getItrId())
            startActivity(intent)
        })

        btnBrowse.setOnClickListener(View.OnClickListener {
            uploadDiloag()
        })
        if (ConnectionDetector().isConnectingToInternet(mContext)) {
            getDocuments()
        } else {
            Toast.makeText(
                mContext,
                "Please Check Your Internet Connection",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    override fun getToolbarTitle(): String? {
        return "Upload form 16"
    }

    override fun getTagName(): String? {
        return "ITR"
    }

    override fun getLayoutResource(): Int {
        return R.layout.activity_document_upload
    }


    fun uploadDiloag() {
        val items = arrayOf(
            "Select photo from gallery",
            "Capture photo from camera",
            "Select PDF of the document"
        )
        AlertDialog.Builder(this)
            .setTitle("Select Action")
            .setItems(items, DialogInterface.OnClickListener { _, which ->
                when (which) {
                    0 -> {
                        val galleryIntent = Intent(
                            Intent.ACTION_PICK,
                            android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI
                        )
                        startActivityForResult(galleryIntent, GALLERY)
                    }
                    1 -> {
                        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                        startActivityForResult(intent, CAMERA)
                    }
                    2 -> {
                        val intent4 = Intent(this, NormalFilePickActivity::class.java)
                        intent4.putExtra(Constant.MAX_NUMBER, 9)
                        intent4.putExtra(IS_NEED_FOLDER_LIST, true)
                        intent4.putExtra(
                            NormalFilePickActivity.SUFFIX,
                            arrayOf("pdf")
                        )
                        startActivityForResult(intent4, PICK_PDF_CODE)
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

                        if (contentURI != null) {
                            ContentUriUtils.getFilePath(mContext, contentURI)
                        }
//                        val bitmap:Bitmap
                        val bitmap = MediaStore.Images.Media.getBitmap(
                            this.contentResolver,
                            contentURI
                        )
                        postPath = saveImage(bitmap)
                        uploadConfirm()
                        setFilePathsInToModel(postPath!!)

                    } catch (e: IOException) {
                        e.printStackTrace()
                    }
                }
                CAMERA -> {
                    val thumbnail = data!!.extras!!.get("data") as Bitmap
                    postPath = saveImage(thumbnail)
//                    setFilePathsInToModel(postPath!!)
                    uploadConfirm()
                }
                PICK_PDF_CODE -> {
                    val list =
                        data!!.getParcelableArrayListExtra<NormalFile>(Constant.RESULT_PICK_FILE)
                    for (file in list) {
                        postPath = file.path
                    }
                    uploadConfirm()
                }
            }
        }


    }


    private fun setFilePathsInToModel(path: String) {
        if (mForm16a) {
            objApiLogin.Form16Url = path
        }
        if (mForm16b) {
            objApiLogin.Form16Url2 = path
        }
        if (mAadhar) {
            objApiLogin.AdhaarUrl = path
        }
        if (mPan) {
            objApiLogin.PanUrl = path
        }
        if (mOtherDoc) {
            objApiLogin.OtherDocUrl = path
        }

    }

    private fun saveImage(myBitmap: Bitmap): String {
        val bytes = ByteArrayOutputStream()
        myBitmap.compress(Bitmap.CompressFormat.JPEG, 90, bytes)
        val wallpaperDirectory = File((Environment.getExternalStorageDirectory()).toString())
        if (!wallpaperDirectory.exists()) {
            wallpaperDirectory.mkdirs()
        }
        try {
            val f = File(
                wallpaperDirectory, ((Calendar.getInstance()
                    .timeInMillis).toString() + ".jpg")
            )
            f.createNewFile()
            val fo = FileOutputStream(f)
            fo.write(bytes.toByteArray())
            MediaScannerConnection.scanFile(
                this,
                arrayOf(f.path),
                arrayOf("image/jpeg"), null
            )
            fo.close()
            return f.absolutePath
        } catch (e1: IOException) {
            e1.printStackTrace()
        }
        return ""
    }


    private fun uploadConfirm() {
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
            uploadFile()
        })
        alertDialog.setNegativeButton("Cancel", DialogInterface.OnClickListener { _, _ ->
            password = ""
            uploadFile()
        })
        alertDialog.show()
    }


    private fun uploadFile() {
        if (postPath == null || postPath == "") {
            Toast.makeText(this, "Kindly select one file ", Toast.LENGTH_LONG).show()
            return
        } else {
            var dialog = AlertDialogueManager(mContext, "Please Wait")
            val file = File(postPath!!)
            val mFile = RequestBody.create("*/*".toMediaTypeOrNull(), file)
            val ITRID = RequestBody.create(
                "text/plain".toMediaTypeOrNull(),
                itrBaseModel.getItrId().toString()
            )
            val mFileType = RequestBody.create(
                "text/plain".toMediaTypeOrNull(),
                com.example.tax.utils.Constant.FILE_TYPE_SALARY
            )
            val mFilePassword = RequestBody.create("text/plain".toMediaTypeOrNull(), password)
            val body = MultipartBody.Part.createFormData("filecontents", file.name, mFile)

            val call = apI_Interface.uploadFile(body, ITRID, mFileType, mFilePassword)
            call.enqueue(object : Callback<ResponseBody> {
                override fun onResponse(
                    call: Call<ResponseBody>,
                    response: Response<ResponseBody>
                ) {
                    try {
                        if (response.isSuccessful) {
                            dialog.hideDialog()
                            var respObj = response.body()?.string()
                            val gson = Gson()
                            val documentsModel: DocumentsModel =
                                gson.fromJson(respObj, DocumentsModel::class.java)

                            if (documentsModel != null && documentsModel.status.equals("ok")) {
                                recyclerImgList.setLayoutManager(
                                    LinearLayoutManager(mContext, RecyclerView.HORIZONTAL, false)
                                )
                                recyclerImgList.adapter = DocumentsListAdapter(
                                    mContext,
                                    documentsModel.files,
                                    this@DocumentUploadActivity
                                )
                            }
                        } else {
                            dialog.hideDialog()
                            Toast.makeText(
                                applicationContext,
                                "problem uploading file",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    } catch (e: Exception) {
                        e.printStackTrace()
                        dialog.hideDialog()
                        Toast.makeText(
                            mContext,
                            resources.getString(R.string.error_message),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }

                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    dialog.hideDialog()
                    Toast.makeText(
                        mContext,
                        resources.getString(R.string.error_message),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            })
        }
    }


    fun deleteDocuments(files: Files) {
        var dialog = AlertDialogueManager(mContext, "Please Wait")
        val call = apI_Interface.deleteDocumentsById(files?.id.toString())
        Log.d("TEMP_TAG", "url: " + call.request().url.toString());
        call.enqueue(object : retrofit2.Callback<DocumentsModel> {
            override fun onFailure(call: Call<DocumentsModel>, t: Throwable) {
                dialog.hideDialog()
                toast("Please Try Again")
            }

            override fun onResponse(
                call: Call<DocumentsModel>,
                response: Response<DocumentsModel>
            ) {
                if (response.isSuccessful) {
                    dialog.hideDialog()
                    var gson: Gson = Gson()
                    var jsonObj: String = gson.toJson(response.body())

                    var doc = response.body()
                    if (doc != null && doc?.status == "ok") {
                        mContext.toast("Deleted Successfully !!")
                        getDocuments()
//                        recyclerImgList.setLayoutManager(
//                            LinearLayoutManager(mContext, RecyclerView.HORIZONTAL, false))
//                        recyclerImgList.adapter = DocumentsListAdapter(mContext,doc.files,this@DocumentUploadActivity)
                    }
                }
            }

        })
    }


    private fun getDocuments() {
        var dialog = AlertDialogueManager(mContext, "Please Wait")
        val call = apI_Interface.getDocumentsByItrid(itrBaseModel.getItrId().toString())
        Log.d("TEMP_TAG", "url: " + call.request().url.toString());
        call.enqueue(object : retrofit2.Callback<DocumentsModel> {
            override fun onFailure(call: Call<DocumentsModel>, t: Throwable) {
                dialog.hideDialog()
                toast("Please Try Again")
            }

            override fun onResponse(
                call: Call<DocumentsModel>,
                response: Response<DocumentsModel>
            ) {
                if (response.isSuccessful) {
                    dialog.hideDialog()
                    var gson: Gson = Gson()
                    var jsonObj: String = gson.toJson(response.body())

                    var doc = response.body()
                    if (doc != null && doc?.status == "ok") {
                        recyclerImgList.setLayoutManager(
                            LinearLayoutManager(mContext, RecyclerView.HORIZONTAL, false)
                        )
                        recyclerImgList.adapter =
                            DocumentsListAdapter(mContext, doc.files, this@DocumentUploadActivity)
                    }
                }
            }

        })
    }


    private fun checkAndRequestPermissions(): Boolean {
        val listPermissionsNeeded = arrayListOf<String>()
        for (i in 0 until appPermissions.size) {
            if (ContextCompat.checkSelfPermission(
                    mContext,
                    appPermissions[i].toString()
                ) != PackageManager.PERMISSION_GRANTED
            )
                listPermissionsNeeded.add(appPermissions[i].toString())
        }

        if (listPermissionsNeeded.isNotEmpty()) {
            ActivityCompat.requestPermissions(
                this,
                listPermissionsNeeded.toArray(arrayOfNulls(listPermissionsNeeded.size)),
                PERMISSIONS_REQUEST_CODE
            )
            return false
        }

        return true
    }


    override fun deleteDoc(files: Files) {
        deleteDocuments(files)
    }

}
