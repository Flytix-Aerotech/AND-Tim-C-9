package com.aerotech.flytix.model.user


import com.google.gson.annotations.SerializedName

data class DataGetUser(
    @SerializedName("message")
    val message: String,
    @SerializedName("user")
    val user: List<User>
)