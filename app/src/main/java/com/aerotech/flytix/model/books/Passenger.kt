package com.aerotech.flytix.model.books


import com.google.gson.annotations.SerializedName

data class Passenger(
    @SerializedName("birth_date")
    var birthDate: String,
    @SerializedName("booking_id")
    val bookingId: Int,
    @SerializedName("clan_name")
    var clanName: String,
    @SerializedName("full_name")
    var fullName: String,
    @SerializedName("nationality")
    var nationality: String,
    @SerializedName("nik_number")
    var nikNumber: String,
    @SerializedName("passenger_role")
    var passengerRole: String
)