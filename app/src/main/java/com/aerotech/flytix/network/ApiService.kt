package com.aerotech.flytix.network

import com.aerotech.flytix.model.NewUser
import retrofit2.Call
import retrofit2.http.POST

interface ApiService {

    @POST("auth/login")
    fun postLogin(): Call<List<NewUser>>
}