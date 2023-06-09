package com.aerotech.flytix.model.user

import com.google.gson.annotations.SerializedName

data class DataUserLoginItem(
    @SerializedName("email")
    val email: String,
    @SerializedName("password")
    val password: String
)
