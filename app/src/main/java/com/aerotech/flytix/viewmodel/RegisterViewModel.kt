package com.aerotech.flytix.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.aerotech.flytix.model.user.DataSendOtpItem
import com.aerotech.flytix.model.user.DataUserResponse
import com.aerotech.flytix.model.user.NewUser
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

    private val _postOtp: MutableLiveData<DataSendOtpItem> = MutableLiveData()
    val postOtp : LiveData<DataSendOtpItem> = _postOtp
    fun sendOtpRequest(email: String){
        Client.postSendOtp(email).enqueue(object :Callback<DataSendOtpItem>{
            override fun onResponse(call: Call<DataSendOtpItem>, response: Response<DataSendOtpItem>) {
                if (response.isSuccessful){
                    _postOtp.value = response.body()
                    Log.i("data", response.body().toString())
                }
                else{
                    Log.e("onFailure", "Cannot send data")
                }
            }
            override fun onFailure(call: Call<DataSendOtpItem>, t: Throwable) {
                Log.e("onFailure", "a")
            }

        })
    }
}