package com.aerotech.flytix.network

import com.aerotech.flytix.model.books.DataBooksResponse
import com.aerotech.flytix.model.histori.DataHistoriResponse
import com.aerotech.flytix.model.penumpang.PenumpangRequest
import com.aerotech.flytix.model.ticket.DataGetTicketIDResponse
import com.aerotech.flytix.model.ticket.DataGetTicketResponse
import com.aerotech.flytix.model.user.DataSendOtpItem
import com.aerotech.flytix.model.user.DataUserLoginItem
import com.aerotech.flytix.model.user.DataUserProfilePutItem
import com.aerotech.flytix.model.user.DataUserResponse
import com.aerotech.flytix.model.user.DataVerifyOtpItem
import com.aerotech.flytix.model.user.NewUser
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
    fun getFlight() : Call<DataGetTicketResponse>

    @GET("tickets/{id}")
    fun getFlightDetail(@Path("id") id: Int) : Call<DataGetTicketIDResponse>

    @GET("tickets/{id}")
    fun getFlightDetailBack(@Path("id") id: Int) : Call<DataGetTicketIDResponse>

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
    @POST("booking/{id}")
    fun postCheckoutPenumpang(
        @Header("Authorization") token:String,
        @Path("id") id:String,
        @Query("adult") adult:String,
        @Body requestPenumpang: PenumpangRequest
    ):Call<DataBooksResponse>


    @GET("history")
    fun gethistory(
        @Header("Authorization") token:String
    ) : Call<DataHistoriResponse>
}