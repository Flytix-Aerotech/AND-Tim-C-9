package com.aerotech.flytix.model.ticket


import com.google.gson.annotations.SerializedName

data class DataGetTicketResponse(
    @SerializedName("data")
    val `data`: List<DataGetTicketItem>
)