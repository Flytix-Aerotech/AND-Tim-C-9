package com.aerotech.flytix.data.network

import com.aerotech.flytix.model.NewUser
import com.aerotech.flytix.model.UsersData
import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @POST("auth/register")
    fun postRegister(@Body request: NewUser) : Call<UsersData>
}