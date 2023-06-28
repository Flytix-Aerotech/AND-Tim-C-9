package com.aerotech.flytix.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import com.aerotech.flytix.data.MainRepository
import com.aerotech.flytix.data.UserDataStore
import com.aerotech.flytix.model.books.DataBooksResponse
import com.aerotech.flytix.model.books.Passenger
import com.aerotech.flytix.network.ApiService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class BooksViewModel @Inject constructor(
    private val client: ApiService,
    private val repository: MainRepository,
    private val pref:UserDataStore,
    application: Application
) : AndroidViewModel(application) {
    private val _add: MutableLiveData<DataBooksResponse?> = MutableLiveData()
    val add: LiveData<DataBooksResponse?> get() = _add

    private val _add1 = MutableLiveData<String>()
    val add1: LiveData<String> = _add1
    val add2 = _add1.map { it.toUpperCase() }

    fun setNewValue(newValue: String) {
        _add1.value = newValue
    }

    fun addTransaction(
        token: String,
        birthDate : String,
        bookingid: Int,
        clanName: String,
        fullName: String,
        nationality: String,
        nIKNumber: String,
        passengerRole:String

    ) {
        client.addTransaction(token, Passenger(birthDate,bookingid,clanName,fullName,nationality,nIKNumber,passengerRole))
            .enqueue(object : Callback<DataBooksResponse> {
                override fun onResponse(
                    call: Call<DataBooksResponse>,
                    response: Response<DataBooksResponse>
                ) {
                    if (response.isSuccessful) {
                        val responseBody = response.body()
                        if (responseBody != null) {
                            repository.addTransaction(token, Passenger(birthDate,bookingid,clanName,fullName,nationality,nIKNumber,passengerRole))
                            _add.postValue(responseBody)
                        }
                    }
                }

                override fun onFailure(call: Call< DataBooksResponse>, t: Throwable) {
                    _add.postValue(null)
                }
            })
    }

    private val _transaction: MutableLiveData< DataBooksResponse?> = MutableLiveData()
    val transaction: LiveData< DataBooksResponse?> get() = _transaction

    fun getTransactionId(
        id: Int?,
        token: String
    ) {
        client.getTransactionId(token, id)
            .enqueue(object : Callback< DataBooksResponse> {
                override fun onResponse(
                    call: Call< DataBooksResponse>,
                    response: Response< DataBooksResponse>
                ) {
                    if (response.isSuccessful) {
                        val responseBody = response.body()
                        if (responseBody != null) {
                            repository.getTransactionId(token, id)
                            _transaction.postValue(responseBody)
                        }
                    }
                }

                override fun onFailure(call: Call< DataBooksResponse>, t: Throwable) {
                    _transaction.postValue(null)
                }
            })
    }

//    private val _cancelTransaction: MutableLiveData<CancelResponse?> = MutableLiveData()
//    val cancelDataTransaction: LiveData<CancelResponse?> get() = _cancelTransaction
//
//    fun cancelTransaction(
//        id: Int?,
//        token: String
//    ) {
//        client.cancelTransaction(token, id)
//            .enqueue(object : Callback<CancelResponse> {
//                override fun onResponse(
//                    call: Call<CancelResponse>,
//                    response: Response<CancelResponse>
//                ) {
//                    if (response.isSuccessful) {
//                        val responseBody = response.body()
//                        if (responseBody != null) {
//                            repository.cancelTransaction(token, id)
//                            _cancelTransaction.postValue(responseBody)
//                        }
//                    }
//                }
//
//                override fun onFailure(call: Call<CancelResponse>, t: Throwable) {
//                    _cancelTransaction.postValue(null)
//                }
//            })
//    }

    fun getDataStoreToken(): LiveData<String> {
        return pref.getToken.asLiveData()
    }

    fun saveTransactionId(id: Int) {
        viewModelScope.launch {
            pref.saveTransactionId(id)
        }
    }

    fun getId(): LiveData<Int> {
        return pref.getTransactionId.asLiveData()
    }
}