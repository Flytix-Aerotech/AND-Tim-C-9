package com.aerotech.flytix.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.aerotech.flytix.model.books.DataBooksResponse
import com.aerotech.flytix.model.books.Passenger
import com.aerotech.flytix.model.penumpang.PenumpangRequest
import com.aerotech.flytix.network.ApiService
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class BiodataViewModel @Inject constructor(private val apiService: ApiService): ViewModel() {

    private val _getBiodataPenumpangResponse = MutableLiveData<DataBooksResponse>()
    val getBiodataPenumpangResponse: LiveData<DataBooksResponse> = _getBiodataPenumpangResponse

    fun biodataPenumpang(penumpang: PenumpangRequest, token:String){
        apiService.postCheckoutPenumpang("Bearer $token",penumpang).enqueue(object :
            Callback<DataBooksResponse> {
            override fun onResponse(
                call: Call<DataBooksResponse>,
                response: Response<DataBooksResponse>
            ) {
                if (response.isSuccessful){
                    _getBiodataPenumpangResponse.value = response.body()
                } else {
                    Log.e("BiodataViewModel","${response.errorBody()?.string()}")
                }
            }

            override fun onFailure(call: Call<DataBooksResponse>, t: Throwable) {
                Log.e("BiodataViewModel","${t.cause}")
            }

        })
    }

    private val dataList: MutableList<Passenger> = mutableListOf()
    fun addData(data: Passenger) {
        dataList.add(data)
    }
    fun getDataList(): List<Passenger> {
        return dataList
    }
}