package com.aerotech.flytix.model

import com.google.gson.annotations.SerializedName

data class DataUserProfilePutItem(
    @SerializedName("email")
    val email: String,
    @SerializedName("full_name")
    val fullName: String,
    @SerializedName("phone_number")
    val phoneNumber: String,
    @SerializedName("username")
    val username: String
)
