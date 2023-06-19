package com.aerotech.flytix.model.user


import com.google.gson.annotations.SerializedName

data class DataUserResponse(
<<<<<<<< HEAD:app/src/main/java/com/aerotech/flytix/model/user/DataUserResponse.kt
    @SerializedName("status")
    val status: String,
========
>>>>>>>> 3b70c3d (update profile (manual token)):app/src/main/java/com/aerotech/flytix/model/DataUserResponse.kt
    @SerializedName("message")
    val message: String,
    @SerializedName("token")
    val token: String,
    @SerializedName("user")
    val user: NewUser
)