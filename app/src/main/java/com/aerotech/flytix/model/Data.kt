package com.aerotech.flytix.model


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("airport")
    val airport: Airport,
    @SerializedName("airport_id")
    val airportId: Int,
    @SerializedName("createdAt")
    val createdAt: String,
    @SerializedName("flight")
    val flight: FlightX,
    @SerializedName("flight_id")
    val flightId: Int,
    @SerializedName("id")
    val id: Int,
    @SerializedName("passenger_id")
    val passengerId: Int,
    @SerializedName("price")
    val price: Int,
    @SerializedName("type_of_class")
    val typeOfClass: String,
    @SerializedName("updatedAt")
    val updatedAt: String
)