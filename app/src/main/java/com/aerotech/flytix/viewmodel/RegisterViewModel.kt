package com.aerotech.flytix.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.aerotech.flytix.model.UserRegister
import com.aerotech.flytix.model.UserX
import com.aerotech.flytix.model.otp
import com.aerotech.flytix.model.verify_otp
import com.aerotech.flytix.network.ApiService
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(private val Client: ApiService): ViewModel() {
    private var livedataUser: MutableLiveData<UserRegister> = MutableLiveData()
    val dataPostUser: LiveData<UserRegister> get() = livedataUser
    fun postUserRegister(dataUsers: UserX) {
        //memakai callback yang retrofit
        Client.postRegister(dataUsers).enqueue(object : Callback<UserRegister> {
            override fun onResponse(
                call: Call<UserRegister>,
                response: Response<UserRegister>
            ) {
                if (response.isSuccessful) {
                    val data = response.body()
                    livedataUser.postValue(data!!)
                } else {
                    Log.e("Error: ", "On Failure: ${response.body()!!}",)
                }
            }
            override fun onFailure(call: Call<UserRegister>, t: Throwable) {
                Log.e("Error: ", "onFailure : ${t.message}")
            }
        })
    }
    private val _postOtp: MutableLiveData<otp> = MutableLiveData()
    val postOtp : LiveData<otp> = _postOtp
    fun sendOtpRequest(email: String){
        Client.postSendOtp(email).enqueue(object :Callback<otp>{
                override fun onResponse(call: Call<otp>, response: Response<otp>) {
                    if (response.isSuccessful){
                        _postOtp.value = response.body()
                        Log.i("data", response.body().toString())
                    }
                    else{
                        Log.e("onFailure", "Cannot send data")
                    }
                }
                override fun onFailure(call: Call<otp>, t: Throwable) {
                    Log.e("onFailure", "a")
                }

            })
    }
}

