package com.aerotech.flytix.network

import com.aerotech.flytix.model.DataUserLoginItem
import com.aerotech.flytix.model.DataUserResponse
import com.aerotech.flytix.model.NewUser
import com.aerotech.flytix.model.User
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
    @GET("auth/getusers")
    fun postUserLogin(): Call<User>
}