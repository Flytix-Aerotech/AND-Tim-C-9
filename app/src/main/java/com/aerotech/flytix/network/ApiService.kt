package com.aerotech.flytix.network

import com.aerotech.flytix.model.DataUserLoginItem
import com.aerotech.flytix.model.DataUserResponse
import com.aerotech.flytix.model.NewUser
import retrofit2.Call
import retrofit2.http.*

interface ApiService {

    @POST("auth/register")
    fun postRegister(@Body request: NewUser) : Call<DataUserResponse>
    @POST("auth/login")
    fun postLogin(@Body request: DataUserLoginItem): Call<DataUserResponse>
}