package com.aerotech.flytix.model.user


import com.google.gson.annotations.SerializedName

data class NewUser(
    @SerializedName("email")
    val email: String,
    @SerializedName("full_name")
    val fullName: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("password")
    val password: String,
    @SerializedName("phone_number")
    val phoneNumber: String,
    @SerializedName("role")
    val role: String,
    @SerializedName("username")
    val username: String,
    @SerializedName("verify")
    val verify: Boolean
)