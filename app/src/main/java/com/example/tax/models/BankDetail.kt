package com.example.tax.models

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName




class BankDetail {

    @SerializedName("id")
    @Expose
    private var id: String? = null

    @SerializedName("uuid")
    @Expose
    private var uuid: String? = null

    @SerializedName("UserId")
    @Expose
    private var userId: String? = null

    @SerializedName("BankName")
    @Expose
    private var bankName: String? = null

    @SerializedName("IfscCode")
    @Expose
    private var ifscCode: String? = null

    @SerializedName("AccountNumber")
    @Expose
    private var accountNumber: String? = null

    @SerializedName("IsPrimary")
    @Expose
    private var isPrimary: String? = null

    @SerializedName("CreatedAt")
    @Expose
    private var createdAt: String? = null

    @SerializedName("UpdatedAt")
    @Expose
    private var updatedAt: String? = null

    @SerializedName("DeletedAt")
    @Expose
    private var deletedAt: Any? = null

    fun getId(): String? {
        return id
    }

    fun setId(id: String?) {
        this.id = id
    }

    fun getUuid(): String? {
        return uuid
    }

    fun setUuid(uuid: String?) {
        this.uuid = uuid
    }

    fun getUserId(): String? {
        return userId
    }

    fun setUserId(userId: String?) {
        this.userId = userId
    }

    fun getBankName(): String? {
        return bankName
    }

    fun setBankName(bankName: String?) {
        this.bankName = bankName
    }

    fun getIfscCode(): String? {
        return ifscCode
    }

    fun setIfscCode(ifscCode: String?) {
        this.ifscCode = ifscCode
    }

    fun getAccountNumber(): String? {
        return accountNumber
    }

    fun setAccountNumber(accountNumber: String?) {
        this.accountNumber = accountNumber
    }

    fun getIsPrimary(): String? {
        return isPrimary
    }

    fun setIsPrimary(isPrimary: String?) {
        this.isPrimary = isPrimary
    }

    fun getCreatedAt(): String? {
        return createdAt
    }

    fun setCreatedAt(createdAt: String?) {
        this.createdAt = createdAt
    }

    fun getUpdatedAt(): String? {
        return updatedAt
    }

    fun setUpdatedAt(updatedAt: String?) {
        this.updatedAt = updatedAt
    }

    fun getDeletedAt(): Any? {
        return deletedAt
    }

    fun setDeletedAt(deletedAt: Any?) {
        this.deletedAt = deletedAt
    }
}