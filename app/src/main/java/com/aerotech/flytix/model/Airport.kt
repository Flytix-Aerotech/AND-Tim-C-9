package com.aerotech.flytix.model


import com.google.gson.annotations.SerializedName

data class Airport(
    @SerializedName("code")
    val code: String,
    @SerializedName("createdAt")
    val createdAt: Any,
    @SerializedName("id")
    val id: Int,
    @SerializedName("location")
    val location: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("updatedAt")
    val updatedAt: Any
)