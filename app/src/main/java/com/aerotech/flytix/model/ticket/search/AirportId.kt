package com.aerotech.flytix.model.ticket.search


import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class AirportId(
    @SerializedName("code")
    val code: String,
    @SerializedName("location")
    val location: String,
    @SerializedName("name")
    val name: String
)