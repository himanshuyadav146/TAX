package com.example.tax.models

import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose

class File {
    @SerializedName("id")
    @Expose
    var id: String? = null

    @SerializedName("AttachmentId")
    @Expose
    var attachmentId: String? = null

    @SerializedName("ItrId")
    @Expose
    var itrId: String? = null

    @SerializedName("FilePath")
    @Expose
    var filePath: String? = null

    @SerializedName("Type")
    @Expose
    var type: String? = null

    @SerializedName("Password")
    @Expose
    var password: String? = null

    @SerializedName("CreatedAt")
    @Expose
    var createdAt: String? = null

    @SerializedName("UpdatedAt")
    @Expose
    var updatedAt: Any? = null

    @SerializedName("DeletedAt")
    @Expose
    var deletedAt: Any? = null
}