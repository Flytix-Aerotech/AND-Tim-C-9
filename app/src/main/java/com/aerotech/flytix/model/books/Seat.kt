package com.aerotech.flytix.model.books


import com.google.gson.annotations.SerializedName

data class Seat(
    @SerializedName("booking_id")
    val bookingId: Int,
    @SerializedName("flight_id")
    val flightId: Int,
    @SerializedName("seat_number")
    val seatNumber: Int
)