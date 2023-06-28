package com.aerotech.flytix.network

import android.content.Context
import com.aerotech.flytix.data.SearchDataStore
import com.aerotech.flytix.data.UserDataStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitClient {

    private const val  BASE_URL ="https://flytix-c9.up.railway.app/api/v1/"
    private  val logging : HttpLoggingInterceptor
        get(){
            val httpLoggingInterceptor = HttpLoggingInterceptor()
            return httpLoggingInterceptor.apply {
                httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            }
        }

    private val client = OkHttpClient.Builder().addInterceptor(logging).build()

    @Singleton
    @Provides
    fun provideRetrofit() : Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()

    @Singleton
    @Provides
    fun provideFilmApi(retrofit: Retrofit): ApiService =
        retrofit.create(ApiService::class.java)


    @Provides
    fun getSearchManager(@ApplicationContext context: Context): SearchDataStore =
        SearchDataStore(context)

    @Provides
    fun getUserManager(@ApplicationContext context: Context): UserDataStore =
        UserDataStore(context)
}


