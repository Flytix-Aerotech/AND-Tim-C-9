package com.aerotech.flytix.model.passengers


import com.google.gson.annotations.SerializedName

data class DataGetListPassengers(
    @SerializedName("data")
    val `data`: Data,
    @SerializedName("msg")
    val msg: String,
    @SerializedName("status")
    val status: String
)