package com.aerotech.flytix.viewmodel

import androidx.lifecycle.*
import com.aerotech.flytix.data.MainRepository
import com.aerotech.flytix.data.SearchDataStore
import com.aerotech.flytix.model.ticket.destinasifavorite.Data
import com.aerotech.flytix.model.ticket.destinasifavorite.DataGetListTicketsItem
import com.aerotech.flytix.network.ApiService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class DestinasiFavoriteViewModel @Inject constructor(private val Client: ApiService,
                                                     private val repository: MainRepository,
                                                     private val pref: SearchDataStore,
) : ViewModel(){
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

    fun getKotaKeberangkatan(): LiveData<String> {
        return pref.getKotaKeberangkatan.asLiveData()
    }

    fun getKotaDestinasi(): LiveData<String> {
        return pref.getKotaDestinasi.asLiveData()
    }

    fun getTanggalKeberangkatan(): LiveData<String> {
        return pref.getTanggalKeberangkatan.asLiveData()
    }

    fun getTanggalKembali(): LiveData<String> {
        return pref.getTanggalKembali.asLiveData()
    }

    fun simpanKelasKursi(kelasKursi: String) {
        viewModelScope.launch {
            pref.simpanKelasKursi(kelasKursi)
        }
    }
}
