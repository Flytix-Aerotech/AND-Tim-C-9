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
    @POST("auth/verify-account/{email}?")
    fun postVerifyOtp(
        @Path("email") email:String,
        @Field("otp") otp:String
    ): Call<verify_otp>
    @FormUrlEncoded
    @PUT("auth/reset-password/{email}?")
    fun putResetPassword(
        @Path("email") email: String,
        @Field("password") password:String,
        @Field("confirmPassword") confirmPassword:String
    ): Call<ResetPassword>

}
