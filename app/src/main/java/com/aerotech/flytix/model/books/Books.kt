package com.aerotech.flytix.model.books


import com.google.gson.annotations.SerializedName

data class Books(
    @SerializedName("booking_code")
    val bookingCode: String,
    @SerializedName("clan_name")
    val clanName: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("full_name")
    val fullName: String,
    @SerializedName("payment_status")
    val paymentStatus: String,
    @SerializedName("phone_number")
    val phoneNumber: String,
    @SerializedName("ticket_id")
    val ticketId: Int,
    @SerializedName("total_booking")
    val totalBooking: Int,
    @SerializedName("total_price")
    val totalPrice: Int
)