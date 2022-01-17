package com.example.tax.models

data class UserInformation(
    val created_at: String,
    val email: String,
    val id: Int,
    val name: String,
    val phoneno: String,
    val platform: String,
    val status: String,
    val token: String,
    val updated_at: String
)