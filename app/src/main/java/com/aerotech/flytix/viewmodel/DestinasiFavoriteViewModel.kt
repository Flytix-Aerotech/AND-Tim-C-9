package com.aerotech.flytix.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.aerotech.flytix.model.ticket.destinasifavorite.Data
import com.aerotech.flytix.model.ticket.destinasifavorite.DataGetListTicketsItem
import com.aerotech.flytix.network.ApiService
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class DestinasiFavoriteViewModel @Inject constructor(private val Client: ApiService) : ViewModel(){
    var dataDestinasi: MutableLiveData<List<Data>?> = MutableLiveData()
    val destinasi: LiveData<List<Data>?> = dataDestinasi

    fun callApiDestinasi(){
        Client.getDestinasiFavorite().enqueue(object : Callback<DataGetListTicketsItem>{
            override fun onResponse(
                call: Call<DataGetListTicketsItem>,
                response: Response<DataGetListTicketsItem>
            ) {
                if (response.isSuccessful) {
                    dataDestinasi.postValue(response.body()?.data)
                }
                else{
                    //dataDestinasi.postValue(null)
                }
            }

            override fun onFailure(call: Call<DataGetListTicketsItem>, t: Throwable) {
                dataDestinasi.postValue((null))
            }
        })
    }
}
