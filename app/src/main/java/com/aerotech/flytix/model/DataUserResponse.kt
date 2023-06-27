package com.aerotech.flytix.model


import com.google.gson.annotations.SerializedName

data class DataUserResponse(
    @SerializedName("message")
    val message: String,
    @SerializedName("token")
    val token: String,
    @SerializedName("user")
    val user: UserX
)