package com.aerotech.flytix.model.penumpang.request


import com.google.gson.annotations.SerializedName

data class Passenger(
    @SerializedName("birth_date")
    val birthDate: String,
    @SerializedName("booking_id")
    val bookingId: Int,
    @SerializedName("clan_name")
    val clanName: String,
    @SerializedName("full_name")
    val fullName: String,
    @SerializedName("nationality")
    val nationality: String,
    @SerializedName("nik_number")
    val nikNumber: String,
    @SerializedName("passenger_role")
    val passengerRole: String
)