package com.aerotech.flytix.model.ticket.destinasifavorite


import com.google.gson.annotations.SerializedName

data class DataGetListTicketsItem(
    @SerializedName("data")
    val `data`: List<Data>
)