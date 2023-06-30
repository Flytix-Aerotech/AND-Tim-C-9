//package com.aerotech.flytix.viewmodel
//
//import android.app.Application
//import android.util.Log
//import androidx.lifecycle.AndroidViewModel
//import androidx.lifecycle.LiveData
//import androidx.lifecycle.MutableLiveData
//import com.aerotech.flytix.data.MainRepository
//import com.aerotech.flytix.model.tickets.AirportIdX
//import com.aerotech.flytix.model.tickets.DataX
//import com.aerotech.flytix.network.ApiService
//import dagger.hilt.android.lifecycle.HiltViewModel
//import retrofit2.Call
//import retrofit2.Callback
//import retrofit2.Response
//import javax.inject.Inject
//
//@HiltViewModel
//class AirportIDViewModel @Inject constructor(
//    private val client: ApiService,
//    private val repository: MainRepository,
//    application: Application
//) : AndroidViewModel(application) {
//
//    private val _airportCity = MutableLiveData<AirportIdX?>()
//    val LiveDataCityAirport: LiveData<AirportIdX?> = _airportCity
//
//    fun getCityAirport(){
//        client.getAirport().enqueue(object : Callback<DataX> {
//            override fun onResponse(
//                call: Call<DataX>,
//                response: Response<DataX>
//            ) {
//                if (response.isSuccessful) {
//                    repository.getAirport()
//                    _airportCity.postValue(response.body()!!.airportId)
//                }
//            }
//
//            override fun onFailure(call: Call<DataX>, t: Throwable) {
//                Log.e("Error : ", "onFailure: ${t.message}")
//            }
//        })
//    }
//
//}