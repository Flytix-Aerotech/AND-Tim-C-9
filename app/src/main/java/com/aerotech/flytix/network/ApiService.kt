package com.aerotech.flytix.network

import retrofit2.http.POST

interface ApiService {

    @POST("auth/register")
    fun postRegister()
}