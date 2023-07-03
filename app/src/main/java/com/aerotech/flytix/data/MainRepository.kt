package com.aerotech.flytix.data

import com.aerotech.flytix.model.ticket.DataPostticketSearch
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

    fun searchTicketUser(request: DataPostticketSearch) = Client.postSearchTicket(request)

//    fun addTransaction(token: String, request: Passenger) = Client.addTransaction(token, request)
//
//    fun getTransactionId(token: String, id: Int?) = Client.getTransactionId(token, id)

//    fun updatePayment(token: String, request: PaymentRequest) = Client.updatePayment(token, request)
//
//    fun cancelTransaction(token: String, id: Int?) = Client.cancelTransaction(token, id)
//
//    fun getTransactionFilter(token: String, status: String) : Call<TransactionHistory> = Client.getTransactionFilter(token, status)
//
//    fun createNotification(token: String, request: NotificationRequest): Call<NotificationCreateResponse> = Client.createNotification(token, request)
//
//    fun getNotification(token: String): Call<NotificationResponse> = Client.getNotification(token)
//
//    fun getNotificationDetail(token: String, id: Int?): Call<NotificationIdResponse> = Client.getNotificationDetail(token, id)
//
//    fun deleteNotification(token: String, id: Int?): Call<DeleteResponse> = Client.deleteNotification(token, id)


}