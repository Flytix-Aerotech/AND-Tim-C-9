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

    private val liveLoadData = MutableLiveData<Boolean>()
    val loadData: LiveData<Boolean> = liveLoadData
    fun getDataTicket(){
        liveLoadData.value = true
        Client.getTicket().enqueue(object : Callback<DataGetTicketResponse> {
            override fun onResponse(
                call: Call<DataGetTicketResponse>,
                response: Response<DataGetTicketResponse>
            ) {
                if (response.isSuccessful) {
                    liveLoadData.value = false
                    userTicket.postValue(response.body()?.data)
                } else {
                    liveLoadData.value = false
                    Log.e("Error: ", "onFailure : ${response.message()}")
                }
            }

            override fun onFailure(call: Call<DataGetTicketResponse>, t: Throwable) {
                liveLoadData.value = false
                Log.d(ContentValues.TAG, "onFailure: ${t.message}")
            }

        })
    }
}