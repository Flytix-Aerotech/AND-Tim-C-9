package com.aerotech.flytix.viewmodel

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.aerotech.flytix.model.*
import com.aerotech.flytix.network.ApiService
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class TicketViewModel @Inject constructor(var Client: ApiService) : ViewModel(){
    private val userTicket : MutableLiveData<List<TicketX>> = MutableLiveData()
    val _userTicket  : LiveData<List<TicketX>> get() = userTicket
    fun getDataTicket(){
        Client.getTicket().enqueue(object : Callback<List<TicketX>> {
            override fun onResponse(
                call: Call<List<TicketX>>,
                response: Response<List<TicketX>>
            ) {
                if (response.isSuccessful){
                    val data = response.body()
                    if (data != null){
                        userTicket.postValue(data!!)
                    }
                }else{
                    Log.e("Error: ", "onFailure : ${response.message()}")
                }

            }

            override fun onFailure(call: Call<List<TicketX>>, t: Throwable) {
                Log.d(ContentValues.TAG, "onFailure: ${t.message}")
            }

        })
    }
}