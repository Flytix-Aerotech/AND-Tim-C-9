package com.aerotech.flytix.model.histori


import com.google.gson.annotations.SerializedName

data class DataHistoriItem(
    @SerializedName("booking_id")
    val bookingId: Int,
    @SerializedName("history_date")
    val historyDate: String,
    @SerializedName("passenger_id")
    val passengerId: Int,
    @SerializedName("ticket_id")
    val ticketId: Int,
    @SerializedName("user_id")
    val userId: Int
)