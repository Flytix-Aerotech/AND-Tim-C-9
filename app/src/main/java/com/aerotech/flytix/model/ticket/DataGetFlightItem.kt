package com.aerotech.flytix.model.ticket


import com.google.gson.annotations.SerializedName

data class DataGetFlightItem(
    @SerializedName("airline")
    val airline: String,
    @SerializedName("arrival_date")
    val arrivalDate: String,
    @SerializedName("arrival_location")
    val arrivalLocation: String,
    @SerializedName("arrival_time")
    val arrivalTime: String,
    @SerializedName("createdAt")
    val createdAt: Any,
    @SerializedName("departure_date")
    val departureDate: String,
    @SerializedName("departure_location")
    val departureLocation: String,
    @SerializedName("departure_time")
    val departureTime: String,
    @SerializedName("flight_number")
    val flightNumber: String,
    @SerializedName("from_id")
    val fromId: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("to_id")
    val toId: String,
    @SerializedName("type_of_flight")
    val typeOfFlight: String,
    @SerializedName("updatedAt")
    val updatedAt: Any
)