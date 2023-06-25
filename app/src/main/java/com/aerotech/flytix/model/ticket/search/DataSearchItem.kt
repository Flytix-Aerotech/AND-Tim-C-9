package com.aerotech.flytix.model.ticket.search


import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class DataSearchItem(
    @SerializedName("ticketBack")
    var ticketBack: List<DataSearchItemKembali>?,
    @SerializedName("ticketGo")
    var ticketGo: List<DataSearchItemPergi>?
)