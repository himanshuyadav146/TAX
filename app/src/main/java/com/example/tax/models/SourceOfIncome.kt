package com.example.tax.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class SourceOfIncome {
    @SerializedName("isSalary")
    @Expose
    private var isSalary: Boolean? = null

    @SerializedName("isHouseProperty")
    @Expose
    private var isHouseProperty: Boolean? = null

    @SerializedName("isBusiness")
    @Expose
    private var isBusiness: Boolean? = null

    @SerializedName("isCapitalGain")
    @Expose
    private var isCapitalGain: Boolean? = null

    @SerializedName("isOtherSource")
    @Expose
    private var isOtherSource: Boolean? = null

    @SerializedName("isForeignIncome")
    @Expose
    private var isForeignIncome: Boolean? = null

    fun getIsSalary(): Boolean? {
        return isSalary
    }

    fun setIsSalary(isSalary: Boolean?) {
        this.isSalary = isSalary
    }

    fun getIsHouseProperty(): Boolean? {
        return isHouseProperty
    }

    fun setIsHouseProperty(isHouseProperty: Boolean?) {
        this.isHouseProperty = isHouseProperty
    }

    fun getIsBusiness(): Boolean? {
        return isBusiness
    }

    fun setIsBusiness(isBusiness: Boolean?) {
        this.isBusiness = isBusiness
    }

    fun getIsCapitalGain(): Boolean? {
        return isCapitalGain
    }

    fun setIsCapitalGain(isCapitalGain: Boolean?) {
        this.isCapitalGain = isCapitalGain
    }

    fun getIsOtherSource(): Boolean? {
        return isOtherSource
    }

    fun setIsOtherSource(isOtherSource: Boolean?) {
        this.isOtherSource = isOtherSource
    }

    fun getIsForeignIncome(): Boolean? {
        return isForeignIncome
    }

    fun setIsForeignIncome(isForeignIncome: Boolean?) {
        this.isForeignIncome = isForeignIncome
    }
}