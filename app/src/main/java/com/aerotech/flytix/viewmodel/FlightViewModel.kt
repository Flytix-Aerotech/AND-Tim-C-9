package com.aerotech.flytix.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.aerotech.flytix.data.MainRepository
import com.aerotech.flytix.model.ticket.DataGetTicketIDResponse
import com.aerotech.flytix.network.ApiService
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class FlightViewModel @Inject constructor(
    private val client: ApiService,
    private val repository: MainRepository,
    application: Application
) : AndroidViewModel(application) {

    private val getDetailFlight: MutableLiveData<DataGetTicketIDResponse> = MutableLiveData()
    val flightDetail: LiveData<DataGetTicketIDResponse> get() = getDetailFlight

    fun getFlightDetail(id : Int){
        client.getFlightDetail(id)
            .enqueue(object : Callback<DataGetTicketIDResponse> {
                override fun onResponse(
                    call: Call<DataGetTicketIDResponse>,
                    response: Response<DataGetTicketIDResponse>
                ) {
                    if (response.isSuccessful) {
                        val responseBody = response.body()
                        if (responseBody != null) {
                            repository.getFlightDetail(id)
                            getDetailFlight.postValue(response.body())
                        }
                    }
                }

                override fun onFailure(call: Call<DataGetTicketIDResponse>, t: Throwable) {
                }
            })
    }

}