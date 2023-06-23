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
class TicketViewModel @Inject constructor(var Client: ApiService) : ViewModel(){
    private val userTicket : MutableLiveData<List<DataGetTicketItem>?> = MutableLiveData()
    val _userTicket  : LiveData<List<DataGetTicketItem>?> get() = userTicket
    fun getDataTicket(){
        Client.getTicket().enqueue(object : Callback<DataGetTicketResponse> {
            override fun onResponse(
                call: Call<DataGetTicketResponse>,
                response: Response<DataGetTicketResponse>
            ) {
                if (response.isSuccessful) {
                    userTicket.postValue(response.body()?.data)
                } else {
                    Log.e("Error: ", "onFailure : ${response.message()}")
                }
            }

            override fun onFailure(call: Call<DataGetTicketResponse>, t: Throwable) {
                Log.d(ContentValues.TAG, "onFailure: ${t.message}")
            }

        })
    }
}