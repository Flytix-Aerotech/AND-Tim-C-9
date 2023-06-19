package com.aerotech.flytix.model

import com.google.gson.annotations.SerializedName

data class DataUserPostItem(
    @SerializedName("email")
    val email: String,
    @SerializedName("full_name")
    val fullName: String,
    @SerializedName("password")
    val password: String,
    @SerializedName("phone_number")
    val phoneNumber: String,
    @SerializedName("photo")
    val photo: String,
    @SerializedName("username")
    val username: String
)