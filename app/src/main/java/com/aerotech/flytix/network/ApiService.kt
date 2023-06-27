package com.aerotech.flytix.network

import com.aerotech.flytix.model.airport.DataGetAirportResponse
import com.aerotech.flytix.model.ticket.DataGetTicketIDResponse
import com.aerotech.flytix.model.ticket.DataGetTicketResponse
import com.aerotech.flytix.model.ticket.DataPostticketSearch
import com.aerotech.flytix.model.ticket.search.DataSearchTicketResponse
import com.aerotech.flytix.model.user.DataUserLoginItem
import com.aerotech.flytix.model.user.DataUserProfilePutItem
import com.aerotech.flytix.model.user.DataUserResponse
import com.aerotech.flytix.model.user.NewUser
import com.aerotech.flytix.model.user.User
import retrofit2.Call
import retrofit2.http.*

interface ApiService {

    @POST("auth/register")
    fun postRegister(@Body request: NewUser) : Call<DataUserResponse>


//    @POST("auth/login")
//    fun postLogin(@Body request: DataUserLoginItem): Call<DataUserResponse>

    @Headers(
        "Content-Type:application/json",
        "Accept:*/*"
    )
    @POST("auth/login")
    fun postLoginUser(@Body request: DataUserLoginItem): Call<DataUserResponse>

    //    @FormUrlEncoded
//    @POST("auth/login")
//    fun userPostLogin(
//        @Field("email") email:String,
//        @Field("password") password:String
//    ): Call<DataUserResponse>
    @GET("auth/users")
    fun postUserLogin(): Call<User>

    @GET("auth/users")
    fun loginUser(): Call<DataUserResponse>

    @PUT("auth/profile")
    fun putupdateprofile(
        @Header("Authorization") token:String,
        @Body request : DataUserProfilePutItem
    ) : Call<DataUserResponse>

    @GET("auth/profile")
    fun getprofile(
        @Header("Authorization") token:String,
    ) : Call<DataUserResponse>

    @GET("tickets")
    fun getairportLocation(
        @Query("departure_location") location:String,
    ) : Call<DataGetTicketResponse>
    @GET("tickets")
    fun getairport(
    ) : Call<DataGetTicketResponse>

    @GET("tickets")
    fun getTicket(): Call<DataGetTicketResponse>

    //    ticket
    @GET("tickets")
    fun getFlight() : Call<DataGetTicketResponse>

    @GET("tickets/{id}")
    fun getFlightDetail(@Path("id") id: Int) : Call<DataGetTicketIDResponse>

    @GET("airports")
    fun getAirport() : Call<DataGetAirportResponse>


    @Headers(
        "Content-Type:application/json",
        "Accept:*/*"
    )
    @FormUrlEncoded
    @POST("tickets/filter")
    fun postFilterTicket(@Field("departure_date")departureDate: String, @Field("arrival_date")arrivalDate: String,
                         @Field("departure_location")departureLocation: String, @Field("arrival_location")arrivalLocation: String, @Field("type_of_class")typeofClass: String): Call<DataSearchTicketResponse>

    // search
    @GET("tickets/search")
    fun search(@Query("dl")departureLocation : String, @Query("al")arrivalLocation: String,
               @Query("dd")departureDate: String, @Query("toc")typeofClass: String): Call<DataGetTicketResponse>
    @GET("tickets/search")
    fun searchwithoutad(@Query("dl")departureLocation : String, @Query("al")arrivalLocation: String,
                        @Query("dd")departureDate: String, @Query("toc")typeofClass: String): Call<DataGetTicketResponse>


    @Headers(
        "Content-Type:application/json",
        "Accept:*/*"
    )
    @POST("tickets/filter")
    fun postSearchTicket(@Body request: DataPostticketSearch): Call<DataGetTicketResponse>


//    @GET("histories/filter")
//    fun getTransactionFilter(@Header("Authorization")token: String, @Query("status")status: String) : Call<TransactionHistory>
//    @POST("booking")
//    fun addTransaction(@Header("Authorization")token: String, @Body request : AddTransaction) : Call<TransactionResponse>
//
//    @GET("booking/{id}")
//    fun getTransactionId(@Header("Authorization") token: String, @Path("id") id: Int?) : Call<TransactionResponse>
//
//    @DELETE("booking/{id}")
//    fun cancelTransaction(@Header("Authorization") token: String, @Path("id") id: Int?) : Call<CancelResponse>
//    @GET("payment")
//    fun updatePayment(@Header("Authorization") token: String, @Body request: PaymentRequest) : Call<PaymentResponse>
//
//

//    @POST("/api/notification/")
//    fun createNotification(@Header("Authorization") token: String, @Body request: NotificationRequest): Call<NotificationCreateResponse>
//
//    @GET("/api/notification")
//    fun getNotification(@Header("Authorization") token: String): Call<NotificationResponse>
//
//    @GET("/api/notification/{id}")
//    fun getNotificationDetail(@Header("Authorization") token: String, @Path("id") id: Int?): Call<NotificationIdResponse>
//
//    @DELETE("/api/notification/delete/{id}")
//    fun deleteNotification(@Header("Authorization") token: String, @Path("id") id: Int?): Call<DeleteResponse>
}
