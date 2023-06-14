package com.aerotech.flytix.model


import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("createdAt")
    val createdAt: String,
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
    @SerializedName("photo")
    val photo: String,
    @SerializedName("role")
    val role: String,
    @SerializedName("updatedAt")
    val updatedAt: String,
    @SerializedName("username")
    val username: String
)