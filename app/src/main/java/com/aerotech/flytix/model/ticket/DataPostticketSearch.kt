package com.aerotech.flytix.model.ticket

import com.google.gson.annotations.SerializedName

data class DataPostticketSearch(
    @SerializedName("arrival_date")
    val arrivalDate: String?,
    @SerializedName("arrival_location")
    val arrivalLocation: String,
    @SerializedName("departure_date")
    val departureDate: String,
    @SerializedName("departure_location")
    val departureLocation: String,
    @SerializedName("type_of_class")
    val typeOfClass: String,
)
