package com.aerotech.flytix.model

import com.google.gson.annotations.SerializedName

data class ResetPassword(
    @SerializedName("email")
    val email: String,
    @SerializedName("password")
    val password: String,
    @SerializedName("cosfirmPassword")
    val confirmPassword : String
)
