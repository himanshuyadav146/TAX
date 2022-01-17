package com.example.tax.Interfaces

import com.example.tax.models.BankDetail

interface OnBankItemClick {
    fun onClick(bankDetails: BankDetail)
    fun deleteItems(bankDetails: BankDetail)
}