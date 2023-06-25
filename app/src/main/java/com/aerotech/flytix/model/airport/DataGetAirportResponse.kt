package com.aerotech.flytix.model.airport


import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class DataGetAirportResponse(
    @SerializedName("data")
    val `data`: List<DataAirportItem>
)