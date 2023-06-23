package com.aerotech.flytix.model.ticket


import com.google.gson.annotations.SerializedName

data class DataGetAirportItem(
    @SerializedName("arrival_name")
    val arrivalName: String,
    @SerializedName("arrival_terminal")
    val arrivalTerminal: String,
    @SerializedName("createdAt")
    val createdAt: Any,
    @SerializedName("departure_name")
    val departureName: String,
    @SerializedName("departure_terminal")
    val departureTerminal: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("updatedAt")
    val updatedAt: Any
)