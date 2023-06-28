package com.aerotech.flytix.model


import com.google.gson.annotations.SerializedName

data class verify_otp(
    @SerializedName("email")
    val email: String,
    @SerializedName("otp")
    val otp: String,
    @SerializedName("message")
    val message : String
)