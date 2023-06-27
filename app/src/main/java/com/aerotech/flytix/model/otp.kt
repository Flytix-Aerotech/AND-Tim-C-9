package com.aerotech.flytix.model


import com.google.gson.annotations.SerializedName

data class otp(
    @SerializedName("SendOTP")
    val sendOTP: String,
    @SerializedName("email")
    val email: String
)