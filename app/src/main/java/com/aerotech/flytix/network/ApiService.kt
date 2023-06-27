package com.aerotech.flytix.network

import com.aerotech.flytix.model.*
import retrofit2.Call
import retrofit2.http.*

interface ApiService {

    @POST("auth/register")
    fun postRegister(@Body request: UserX) : Call<UserRegister>

    @Headers(
        "Content-Type:application/json",
        "Accept:*/*"
    )
    @POST("auth/login")
    fun postLoginUser(@Body request: DataUserLoginItem): Call<DataUserResponse>
    @GET("auth/users")
    fun postUserLogin(): Call<User>

    @GET("tickets")
    fun getTicket(): Call<List<TicketX>>

    @FormUrlEncoded
    @POST("auth/send/email-otp")
    fun postSendOtp(@Field("email") email: String): Call<otp>

    @FormUrlEncoded
    @POST("auth/verify-otp/{email}?")
    fun postVerifyOtp(
        @Path("email") email:String,
        @Field("otp") otp:String
    ): Call<verify_otp>

}
