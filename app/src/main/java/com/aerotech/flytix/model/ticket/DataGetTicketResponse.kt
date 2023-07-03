package com.aerotech.flytix.model.ticket


import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class DataGetTicketResponse(
    @SerializedName("data")
    val `data`: List<DataGetTicketItem>
)