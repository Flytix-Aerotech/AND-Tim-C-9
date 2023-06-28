package com.aerotech.flytix.model.books


import com.google.gson.annotations.SerializedName

data class DataBooksResponse(
    @SerializedName("books")
    val books: Books,
    @SerializedName("passengers")
    val passengers: List<Passenger>,
    @SerializedName("seats")
    val seats: List<Seat>
)