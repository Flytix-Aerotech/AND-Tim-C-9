package com.aerotech.flytix.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.aerotech.flytix.model.DataUserResponse
import com.aerotech.flytix.model.NewUser
import com.aerotech.flytix.network.ApiService
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(private val Client: ApiService) : ViewModel() {
    private var livedataUser: MutableLiveData<DataUserResponse> = MutableLiveData()
    val dataPostUser: LiveData<DataUserResponse> get() = livedataUser
    fun postUserRegister(dataUsers: NewUser) {
        //memakai callback yang retrofit
        Client.postRegister(dataUsers).enqueue(object : Callback<DataUserResponse> {
            override fun onResponse(
                call: Call<DataUserResponse>,
                response: Response<DataUserResponse>

            ) {
                if (response.isSuccessful) {
                    livedataUser.postValue(response.body())
                    Log.i("ResponseSuccess: ", "onSuccess : ${response.body()}")
                } else {
                    Log.e("Error: ", "onFailure : ${response.body()}")
                }
            }

            override fun onFailure(call: Call<DataUserResponse>, t: Throwable) {
                Log.e("Error: ", "onFailure : ${t.message}")
            }
        })
    }
}