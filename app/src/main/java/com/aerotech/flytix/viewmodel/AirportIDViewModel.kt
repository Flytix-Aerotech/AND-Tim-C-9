package com.aerotech.flytix.viewmodel

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.aerotech.flytix.model.ticket.DataGetTicketItem
import com.aerotech.flytix.model.ticket.DataGetTicketResponse
import com.aerotech.flytix.network.ApiService
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class AirportIDViewModel @Inject constructor(private val Client: ApiService) : ViewModel() {
    private val livedataAirportID: MutableLiveData<List<DataGetTicketItem>?> = MutableLiveData()
    val dataAirportId: LiveData<List<DataGetTicketItem>?> get() = livedataAirportID
    fun getAirportLocation(location: String) {
        Client.getairportLocation(location).enqueue(object : Callback<DataGetTicketResponse> {
            override fun onResponse(
                call: Call<DataGetTicketResponse>,
                response: Response<DataGetTicketResponse>
            ) {
                if (response.isSuccessful) {
                    livedataAirportID.postValue(response.body()?.data as List<DataGetTicketItem>?)
                } else {
                    Log.e("Error: ", "onFailure : ${response.message()}")
                }
            }

            override fun onFailure(call: Call<DataGetTicketResponse>, t: Throwable) {
                Log.d(ContentValues.TAG, "onFailure: ${t.message}")
            }

        })

    }


    private val livedataAirport: MutableLiveData<List<DataGetTicketItem>?> = MutableLiveData()
    val dataAirport: LiveData<List<DataGetTicketItem>?> get() = livedataAirport
    fun getAirport() {
        Client.getairport().enqueue(object : Callback<DataGetTicketResponse> {
            override fun onResponse(
                call: Call<DataGetTicketResponse>,
                response: Response<DataGetTicketResponse>
            ) {
                if (response.isSuccessful) {
                    livedataAirport.postValue(response.body()?.data)
                } else {
                    Log.e("Error: ", "onFailure : ${response.message()}")
                }
            }

            override fun onFailure(call: Call<DataGetTicketResponse>, t: Throwable) {
                Log.d(ContentValues.TAG, "onFailure: ${t.message}")
            }

        })
//    private var livedataAirportID: MutableLiveData<DataGetTicketResponse> = MutableLiveData()
//    val dataAirportId: LiveData<DataGetTicketResponse> get() = livedataAirportID
//    fun getAirportID(code:String) {
//        //memakai callback yang retrofit
//        Client.getairportId(code).enqueue(object : Callback<DataGetTicketResponse> {
//            override fun onResponse(
//                call: Call<DataGetTicketResponse>,
//                response: Response<DataGetTicketResponse>
//
//            ) {
//                if (response.isSuccessful) {
//                    livedataAirportID.postValue(response.body())
//                    Log.i("ResponseSuccess: ", "onSuccess : ${response.body()}")
//                } else {
//                    Log.e("Error: ", "onFailure : ${response.body()}")
//                }
//            }
//
//            override fun onFailure(call: Call<DataGetTicketResponse>, t: Throwable) {
//                Log.e("Error: ", "onFailure : ${t.message}")
//            }
//        })
//    }
    }
}