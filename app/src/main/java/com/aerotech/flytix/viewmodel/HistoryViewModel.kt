package com.aerotech.flytix.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.aerotech.flytix.model.histori.DataHistoriItem
import com.aerotech.flytix.model.histori.DataHistoriResponse
import com.aerotech.flytix.network.ApiService
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class HistoryViewModel @Inject constructor(var api: ApiService) : ViewModel() {
    val _history: MutableLiveData<DataHistoriItem> = MutableLiveData()
    val livehistory: LiveData<DataHistoriItem> = _history

    fun gethistory(token: String) {
        api.gethistory("Bearer $token").enqueue(object : Callback<DataHistoriResponse> {
            override fun onResponse(
                call: Call<DataHistoriResponse>,
                response: Response<DataHistoriResponse>
            ) {
                if (response.isSuccessful) {
                    _history.postValue(response.body()!!.data)
                } else {
                    Log.e("History ViewModel", "Cannot get data1")
                }
            }

            override fun onFailure(call: Call<DataHistoriResponse>, t: Throwable) {
                Log.e("History ViewModel", "Cannot get data2")
            }

        })

    }
}