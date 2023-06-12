package com.aerotech.flytix.model


import com.google.gson.annotations.SerializedName

data class UsersData(
    @SerializedName("message")
    val message: String,
    @SerializedName("newUser")
    val newUser: NewUser
)