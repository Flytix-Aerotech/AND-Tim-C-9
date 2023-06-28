package com.aerotech.flytix.model.user

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class DataUserProfilePutItem(
    @SerializedName("email")
    val email: String,
    @SerializedName("full_name")
    val fullName: String,
    @SerializedName("phone_number")
    val phoneNumber: String,
    @SerializedName("username")
    val username: String
):Serializable
