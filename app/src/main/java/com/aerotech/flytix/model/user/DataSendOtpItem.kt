package com.aerotech.flytix.model.user

import com.google.gson.annotations.SerializedName

data class DataSendOtpItem(
    @SerializedName("SendOTP")
    val sendOTP: String,
    @SerializedName("email")
    val email: String
)
