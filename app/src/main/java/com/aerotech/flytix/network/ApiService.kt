package com.aerotech.flytix.network

import com.aerotech.flytix.model.DataUserLoginItem
import com.aerotech.flytix.model.DataUserProfilePutItem
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

    @POST("auth/login")
    fun postLoginUser(@Body request: DataUserLoginItem): Call<DataUserResponse>

    @FormUrlEncoded
    @POST("auth/login")
    fun userPostLogin(
        @Field("email") email:String,
        @Field("password") password:String
    ): Call<DataUserResponse>
    @GET("auth/users")
    fun postUserLogin(): Call<User>

    @GET("auth/users")
    fun loginUser(): Call<DataUserResponse>

    @PUT("auth/profile")
    fun putupdateprofile(
        @Header("Authorization") token:String,
        @Body request : DataUserProfilePutItem
    ) : Call<DataUserResponse>

    @GET("auth/profile")
    fun getprofile(
        @Header("Authorization") token:String,
    ) : Call<DataUserResponse>
}