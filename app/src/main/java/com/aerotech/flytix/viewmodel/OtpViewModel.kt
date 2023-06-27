package com.aerotech.flytix.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.aerotech.flytix.model.verify_otp
import com.aerotech.flytix.network.ApiService
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject
@HiltViewModel
class OtpViewModel @Inject constructor(private val Client: ApiService): ViewModel() {
    private val _verifOtp: MutableLiveData<verify_otp> = MutableLiveData()
    val verifOtp : LiveData<verify_otp> = _verifOtp
    fun sendOVerifOtp(email: String, otp:String){
        Client.postVerifyOtp(email, otp).enqueue(object : Callback<verify_otp> {
            override fun onResponse(call: Call<verify_otp>, response: Response<verify_otp>) {
                if (response.isSuccessful){
                    _verifOtp.value = response.body()
                }
                else{
                    Log.e("onFailure", "onFailure: Code Salah")
                }
            }
            override fun onFailure(call: Call<verify_otp>, t: Throwable) {
                Log.e("onFailure", "onFailure : ${t.message}")
            }

        })
    }
}