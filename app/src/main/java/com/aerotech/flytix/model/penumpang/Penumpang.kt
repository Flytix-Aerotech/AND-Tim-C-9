package com.aerotech.flytix.model.penumpang

import com.aerotech.flytix.model.books.Passenger
import com.aerotech.flytix.model.books.Seat
import com.google.gson.annotations.SerializedName

data class PenumpangData(
    @SerializedName("birth_date")
    var birthDate: String,
    @SerializedName("booking_id")
    var bookingId: Int,
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

data class ticket(
//    @SerializedName("clan_name")
//    val clanName: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("full_name")
    val fullName: String,
    @SerializedName("phone_number")
    val phoneNumber: String,
    @SerializedName("ticket_id")
    val ticketId: Int,
    @SerializedName("total_price")
    val totalPrice: Int
)


data class PenumpangRequest(
    @SerializedName("books")
    val books: ticket,
    @SerializedName("passengers")
    val passengers: List<Passenger>,
    @SerializedName("seats")
    val seats: List<Seat>
)


data class Penumpang(
    var penumpang: String,
    var role: String
)