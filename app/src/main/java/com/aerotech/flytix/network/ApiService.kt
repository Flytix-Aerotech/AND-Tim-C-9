package com.aerotech.flytix.network

import com.aerotech.flytix.model.DataUserLoginItem
import com.aerotech.flytix.model.DataUserResponse
import com.aerotech.flytix.model.NewUser
import com.aerotech.flytix.model.User
import com.aerotech.flytix.model.updateprofile.PutDataUpdateProfile
import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @POST("auth/register")
    fun postRegister(@Body request: NewUser) : Call<DataUserResponse>

    @Headers(
        "Content-Type:application/json",
        "Accept:*/*"
    )
    @POST("auth/login")
    fun postLogin(@Body request: DataUserLoginItem): Call<DataUserResponse>

    @POST("auth/login")
    fun postLoginUser(@Body request: DataUserLoginItem): Call<DataUserResponse>
    @GET("auth/users")
    fun postUserLogin(): Call<User>

//    @Headers(
//        "Content-Type:multipart/form-data; boundary=<calculated when request is sent>",
//        "Authorization:Bearer{token}"
//    )
    @GET("auth/profile")
    fun getProfile(
        @Header("Authorization") token: String):Call<DataUserResponse>

    @PUT("auth/profile")
    fun updateProfile(@Header("Authorization") token: String,
    @Body putdataUser : PutDataUpdateProfile
    ) : Call<DataUserResponse>



}
