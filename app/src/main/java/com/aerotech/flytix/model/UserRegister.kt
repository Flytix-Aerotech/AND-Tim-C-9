package com.aerotech.flytix.model


import com.google.gson.annotations.SerializedName

data class UserRegister(
    @SerializedName("msg")
    val msg: String,
    @SerializedName("user")
    val user: UserX
)