package com.aerotech.flytix.data

import com.aerotech.flytix.network.ApiService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MainRepository @Inject constructor(private val Client: ApiService) {
    fun getFlight() = Client.getFlight()

    fun getFlightDetail(id: Int) = Client.getFlightDetail(id)
    fun getFlightDetailBack(id: Int) = Client.getFlightDetailBack(id)

//    fun getAirport() = Client.getAirport()

    fun search(departureLocation : String,
               arrivalLocation: String,
               departureDate: String,
               tOc: String) = Client.search(departureLocation, arrivalLocation, departureDate,tOc)

    fun searchTicketow(departureLocation : String,
               arrivalLocation: String,
               departureDate: String,
               tOc: String) = Client.searchTicketOw(departureLocation, arrivalLocation, departureDate,tOc)

    fun searchroundtrip(departureLocation : String,
               arrivalLocation: String,
               arrivalDate: String,
               tOc: String) = Client.searchwithad(departureLocation, arrivalLocation, arrivalDate,tOc)
}