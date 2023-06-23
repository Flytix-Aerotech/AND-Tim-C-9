package com.aerotech.flytix.model.user


import com.google.gson.annotations.SerializedName

data class DataUserResponse(
    @SerializedName("status")
    val status: String,
    @SerializedName("message")
    val message: String,
    @SerializedName("token")
    val token: String,
    @SerializedName("user")
    val user: NewUser
)