package com.aerotech.flytix.model


import com.google.gson.annotations.SerializedName

data class Ticket(
    @SerializedName("tickets")
    val tickets: List<TicketX>
)