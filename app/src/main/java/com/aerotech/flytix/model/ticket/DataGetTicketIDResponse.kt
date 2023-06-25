package com.aerotech.flytix.model.ticket


import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class DataGetTicketIDResponse(
    @SerializedName("data")
    val `data`: DataGetTicketItem
)