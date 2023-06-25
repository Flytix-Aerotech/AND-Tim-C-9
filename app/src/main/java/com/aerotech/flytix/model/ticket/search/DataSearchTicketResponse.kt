package com.aerotech.flytix.model.ticket.search


import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class DataSearchTicketResponse(
    @SerializedName("data")
    val `data`: DataSearchItem,
    @SerializedName("status")
    val status: String
)