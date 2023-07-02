package com.aerotech.flytix.network

import com.aerotech.flytix.model.penumpang.PenumpangRequest
import com.aerotech.flytix.model.ticket.DataGetTicketIDResponse
import com.aerotech.flytix.model.ticket.DataGetTicketResponse
import com.aerotech.flytix.model.ticket.DataPostticketSearch
import com.aerotech.flytix.model.ticket.destinasifavorite.DataGetListTicketsItem
import com.aerotech.flytix.model.user.*
import com.dwiki.tiketku.model.penumpang.ResponsePenumpang
import retrofit2.Call
import retrofit2.http.*

interface ApiService {

    // Service User
    @POST("auth/register")
    fun postRegister(@Body request: NewUser) : Call<DataUserResponse>

    @Headers(
        "Content-Type:application/json",
        "Accept:*/*"
    )
    @POST("auth/login")
    fun postLoginUser(@Body request: DataUserLoginItem): Call<DataUserResponse>

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

    @FormUrlEncoded
    @POST("auth/send/email-otp")
    fun postSendOtp(@Field("email") email: String): Call<DataSendOtpItem>

    @FormUrlEncoded
    @POST("auth/verify-account/{email}?")
    fun postVerifyOtp(
        @Path("email") email:String,
        @Field("otp") otp:String
    ): Call<DataVerifyOtpItem>


    //Ticket Service
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

    // search
    @GET("tickets/search")
    fun search(@Query("dl")departureLocation : String, @Query("al")arrivalLocation: String,
               @Query("dd")departureDate: String, @Query("toc")typeofClass: String): Call<DataGetTicketResponse>

    @GET("tickets/search")
    fun searchTicketOw(@Query("dl")departureLocation : String, @Query("al")arrivalLocation: String,
                       @Query("dd")departureDate: String, @Query("toc")typeofClass: String): Call<DataGetTicketResponse>

    @GET("tickets/search")
    fun searchTicketRt(@Query("dl")departureLocation : String, @Query("al")arrivalLocation: String,
                       @Query("dd")arrivalDate: String, @Query("toc")typeofClass: String): Call<DataGetTicketResponse>

    @GET("tickets/search")
    fun searchwithad(@Query("dl")departureLocation : String, @Query("al")arrivalLocation: String,
                     @Query("dd")arrivalDate: String, @Query("toc")typeofClass: String): Call<DataGetTicketResponse>


    @Headers(
        "Content-Type:application/json",
        "Accept:*/*"
    )
    @POST("tickets/filter")
    fun postSearchTicket(@Body request: DataPostticketSearch): Call<DataGetTicketResponse>


    //Transaction Service
//    @POST("booking")
//    fun addTransaction(@Header("Authorization")token: String, @Body request : Passenger) : Call<DataBooksResponse>
//
//    @GET("booking/{id}")
//    fun getTransactionId(@Header("Authorization") token: String, @Path("id") id: Int?) : Call<DataBooksResponse>

    @Headers(
        "Content-Type:application/json",
        "Accept:*/*"
    )
    @POST("checkout")
    fun postCheckoutPenumpang(
        @Header("Authorization") token:String,
        @Body requestPenumpang: PenumpangRequest
    ):Call<ResponsePenumpang>

    @GET("tickets")
    fun getDestinasiFavorite(): Call<DataGetListTicketsItem>

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