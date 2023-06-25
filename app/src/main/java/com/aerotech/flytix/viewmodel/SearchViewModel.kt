package com.aerotech.flytix.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.aerotech.flytix.data.MainRepository
import com.aerotech.flytix.data.SearchDataStore
import com.aerotech.flytix.model.ticket.DataGetTicketItem
import com.aerotech.flytix.model.ticket.DataGetTicketResponse
import com.aerotech.flytix.model.ticket.DataPostticketSearch
import com.aerotech.flytix.network.ApiService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val client: ApiService,
    private val repository: MainRepository,
    private val pref: SearchDataStore,
    application: Application
) : AndroidViewModel(application) {

    private val _search: MutableLiveData<List<DataGetTicketItem>?> = MutableLiveData()
    fun getLiveDataSearch(): MutableLiveData<List<DataGetTicketItem>?> = _search

    fun getDataSearch(
        departureLocation: String,
        arrivalLocation: String,
        departureDate: String,
        tOc: String
    ) {
        client.search(departureLocation, arrivalLocation, departureDate, tOc)
            .enqueue(object : Callback<DataGetTicketResponse> {
                override fun onResponse(
                    call: Call<DataGetTicketResponse>,
                    response: Response<DataGetTicketResponse>
                ) {
                    if (response.isSuccessful) {
                        repository.search(
                            departureLocation,
                            arrivalLocation,
                            departureDate,
                            tOc
                        )
                        _search.postValue(response.body()?.data)
                    } else {
                        _search.postValue(null)
                        Log.d("notSuccess", response.body().toString())
                    }
                }

                override fun onFailure(call: Call<DataGetTicketResponse>, t: Throwable) {
                    _search.postValue(null)
                    Log.d("Failed", t.message.toString())
                }

            })
    }


    private val searchTicketUser: MutableLiveData<List<DataGetTicketItem>?> = MutableLiveData()
    fun getLiveDataSearchTicketUser(): MutableLiveData<List<DataGetTicketItem>?> = searchTicketUser

    fun getDataSearchTicketUser(request: DataPostticketSearch) {
        client.postSearchTicket(request)
            .enqueue(object : Callback<DataGetTicketResponse> {
                override fun onResponse(
                    call: Call<DataGetTicketResponse>,
                    response: Response<DataGetTicketResponse>
                ) {
                    if (response.isSuccessful) {
                        repository.searchTicketUser(request)
                        searchTicketUser.postValue(response.body()?.data)
                        Log.d("Success Search", response.body().toString())
                    } else {
                        searchTicketUser.postValue(null)
                        Log.d("notSuccess", response.body().toString())
                    }
                }

                override fun onFailure(call: Call<DataGetTicketResponse>, t: Throwable) {
                    searchTicketUser.postValue(null)
                    Log.d("Failed", t.message.toString())
                }

            })
    }

    fun simpanKeberangkatan(
        cityDeparture: String,
        cityCodeDeparture: String,
        isDeparture: Boolean
    ) {
        viewModelScope.launch {
            pref.simpanKeberangkatan(cityDeparture, cityCodeDeparture, isDeparture)
        }
    }

    fun simpanKotaKeberangkatan(cityDeparture: String) {
        viewModelScope.launch {
            pref.simpanKotaKeberangkatan(cityDeparture)
        }
    }


    fun simpanTanggalKeberangkatan(departureDate: String) {
        viewModelScope.launch {
            pref.simpanValueTanggalKeberangkatan(departureDate)
        }
    }

    fun simpanTanggalKembali(returnDate: String) {
        viewModelScope.launch {
            pref.simpanValueTanggalKembali(returnDate)
        }
    }

    fun simpanDestinasi(
        cityDestination: String,
        cityCodeDestination: String,
        isDestination: Boolean
    ) {
        viewModelScope.launch {
            pref.simpanDestinasi(cityDestination, cityCodeDestination, isDestination)
        }
    }

    fun simpanKotaDestinasi(cityDestination: String) {
        viewModelScope.launch {
            pref.simpanKotaDestinasi(cityDestination)
        }
    }

    fun simpanTripOneway(isOneway: Boolean) {
        viewModelScope.launch {
            pref.simpanTripOneway(isOneway)
        }
    }

    fun simpanKelasKursi(kelasKursi: String) {
        viewModelScope.launch {
            pref.simpanKelasKursi(kelasKursi)
        }
    }

    fun getKelasKursi(): LiveData<String> {
        return pref.getKelasKursi.asLiveData()
    }

    fun getKotaKeberangkatan(): LiveData<String> {
        return pref.getKotaKeberangkatan.asLiveData()
    }

    fun getKodeKotaKeberangkatan(): LiveData<String> {
        return pref.getKodeKotaKeberangkatan.asLiveData()
    }

    fun getKotaDestinasi(): LiveData<String> {
        return pref.getKotaDestinasi.asLiveData()
    }

    fun getKodeKotaDestinasi(): LiveData<String> {
        return pref.getKodeKotaDestinasi.asLiveData()
    }

    fun getTanggalKeberangkatan(): LiveData<String> {
        return pref.getTanggalKeberangkatan.asLiveData()
    }

    fun getTanggalKembali(): LiveData<String> {
        return pref.getTanggalKembali.asLiveData()
    }

    fun getKeyKeberangkatan(): LiveData<Boolean> {
        return pref.getKeyKeberangkatan.asLiveData()
    }

    fun getKeyDestinasi(): LiveData<Boolean> {
        return pref.getKeyDestinasi.asLiveData()
    }

    fun getValueTanggalKeberangkatan(): LiveData<Boolean> {
        return pref.getValueTanggalKeberangkatan.asLiveData()
    }

    fun getValueTanggalKembali(): LiveData<Boolean> {
        return pref.getValueTanggalKembali.asLiveData()
    }

    fun getValueTripOneway(): LiveData<Boolean> {
        return pref.getValueTripOneway.asLiveData()
    }

    fun hapusKeberangkatan() {
        viewModelScope.launch {
            pref.hapusKeberangkatan()
        }
    }

    fun hapusDestinasi() {
        viewModelScope.launch {
            pref.hapusDestinasi()
        }
    }

    fun hapusTanggalKeberangkatan() {
        viewModelScope.launch {
            pref.hapusTanggalKeberangkatan()
        }
    }

    fun hapusTanggalKembali() {
        viewModelScope.launch {
            pref.hapusTanggalKembali()
        }
    }

    fun hapusOnewayTrip() {
        viewModelScope.launch {
            pref.hapusOnewayTrip()
        }
    }
}