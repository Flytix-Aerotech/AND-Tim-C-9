package com.aerotech.flytix.model.books


import com.google.gson.annotations.SerializedName

data class Seat(
    @SerializedName("seat_number")
    val seatNumber: String
)