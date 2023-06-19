package com.aerotech.flytix.model.updateprofile

import com.google.gson.annotations.SerializedName

data class PutDataUpdateProfile(
    @SerializedName("full_name")
    val fullName: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("username")
    val username: String,
    @SerializedName("phone_number")
    val phoneNumber: String,
    @SerializedName("photo")
    val photo: String

)
