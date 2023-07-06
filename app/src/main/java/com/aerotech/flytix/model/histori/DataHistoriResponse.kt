package com.aerotech.flytix.model.histori


import com.google.gson.annotations.SerializedName

data class DataHistoriResponse(
    @SerializedName("data")
    val `data`: DataHistoriItem,
    @SerializedName("status")
    val status: String
)