package com.aerotech.flytix.model


import com.google.gson.annotations.SerializedName

data class TicketX(
    @SerializedName("airport")
    val airport: Airport,
    @SerializedName("airport_id")
    val airportId: Int,
    @SerializedName("createdAt")
    val createdAt: Any,
    @SerializedName("flight")
    val flight: Flight,
    @SerializedName("flight_id")
    val flightId: Int,
    @SerializedName("id")
    val id: Int,
    @SerializedName("passenger_id")
    val passengerId: Any,
    @SerializedName("price")
    val price: Int,
    @SerializedName("type_of_class")
    val typeOfClass: String,
    @SerializedName("updatedAt")
    val updatedAt: Any
)