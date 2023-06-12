package com.aerotech.flytix.network

import com.aerotech.flytix.model.NewUser
import com.aerotech.flytix.model.RequestLogin
import com.aerotech.flytix.model.UsersData
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {
//    @Headers(
//        "Content-Type : multipart/form-data; boundary=<calculated when request is sent>",
//        "Accept : */*"
//    )
    @POST("auth/register")
    fun postRegister(@Body request: NewUser) : Call<List<NewUser>>

    @Headers(
        "Content-Type:application/json",
        "Accept:*/*"
    )
    @POST("auth/login")
    fun postLogin(@Body request: RequestLogin): Call<UsersData>
}