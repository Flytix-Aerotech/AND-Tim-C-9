package com.aerotech.flytix.model.ticket


import com.google.gson.annotations.SerializedName

data class DataGetTicketItem(
    @SerializedName("airport")
    val airport: DataGetAirportItem,
    @SerializedName("airport_id")
    val airportId: Int,
    @SerializedName("createdAt")
    val createdAt: String,
    @SerializedName("flight")
    val flight: DataGetFlightItem,
    @SerializedName("flight_id")
    val flightId: Int,
    @SerializedName("id")
    val id: Int,
    @SerializedName("price")
    val price: Int,
    @SerializedName("type_of_class")
    val typeOfClass: String,
    @SerializedName("updatedAt")
    val updatedAt: String
)