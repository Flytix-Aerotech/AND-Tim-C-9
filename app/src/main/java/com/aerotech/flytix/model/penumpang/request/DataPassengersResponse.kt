package com.aerotech.flytix.model.penumpang.request


import com.google.gson.annotations.SerializedName

data class DataPassengersResponse(
    @SerializedName("passengers")
    val passengers: List<Passenger>
)