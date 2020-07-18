package com.yourssu.anywherelibrary.data.model

data class SignupRequest(
    val hashedPassword: String,
    val identification: String,
    val nickname: String,
    val universityName: String
)