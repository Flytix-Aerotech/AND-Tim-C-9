package com.aerotech.flytix.model.ticket.search


import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class DataSearchItemKembali(
    @SerializedName("airport_id")
    val airportId: AirportId,
    @SerializedName("flight_id")
    val flightId: FlightId,
    @SerializedName("id")
    val id: Int,
    @SerializedName("price")
    val price: Int,
    @SerializedName("type_of_class")
    val typeOfClass: String
)