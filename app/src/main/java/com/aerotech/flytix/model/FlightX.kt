package com.aerotech.flytix.model


import com.google.gson.annotations.SerializedName

data class FlightX(
    @SerializedName("airline")
    val airline: String,
    @SerializedName("arrival_date")
    val arrivalDate: String,
    @SerializedName("arrival_location")
    val arrivalLocation: String,
    @SerializedName("arrival_time")
    val arrivalTime: String,
    @SerializedName("business_class_price")
    val businessClassPrice: Int,
    @SerializedName("capacity")
    val capacity: Int,
    @SerializedName("createdAt")
    val createdAt: Any,
    @SerializedName("departure_date")
    val departureDate: String,
    @SerializedName("departure_location")
    val departureLocation: String,
    @SerializedName("departure_time")
    val departureTime: String,
    @SerializedName("economy_class_price")
    val economyClassPrice: Int,
    @SerializedName("first_class_price")
    val firstClassPrice: Int,
    @SerializedName("flight_number")
    val flightNumber: Int,
    @SerializedName("from_id")
    val fromId: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("quiet_class_price")
    val quietClassPrice: Int,
    @SerializedName("to_id")
    val toId: String,
    @SerializedName("type_of_flight")
    val typeOfFlight: String,
    @SerializedName("updatedAt")
    val updatedAt: Any
)