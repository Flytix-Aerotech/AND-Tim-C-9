package com.aerotech.flytix.model.user

import com.google.gson.annotations.SerializedName

data class DataVerifyOtpItem(
    @SerializedName("email")
    val email: String,
    @SerializedName("otp")
    val otp: String,
    @SerializedName("msg")
    val message: String
)
