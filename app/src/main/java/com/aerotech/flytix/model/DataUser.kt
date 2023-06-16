package com.aerotech.flytix.model


import com.google.gson.annotations.SerializedName

data class DataUser(
    @SerializedName("message")
    val message: String,
    @SerializedName("user")
    val user: List<User>
)