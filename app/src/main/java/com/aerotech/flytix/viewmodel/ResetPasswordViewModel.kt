package com.aerotech.flytix.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.aerotech.flytix.model.ResetPassword
import com.aerotech.flytix.model.otp
import com.aerotech.flytix.model.verify_otp
import com.aerotech.flytix.network.ApiService
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class ResetPasswordViewModel@Inject constructor(private val Client: ApiService): ViewModel() {
    private val _resetPassword: MutableLiveData<ResetPassword> = MutableLiveData()
    val resetPassword : LiveData<ResetPassword> = _resetPassword
    fun putResetPassword(email: String, password:String, confirmPass: String){
        Client.putResetPassword(email, password, confirmPass).enqueue(object : Callback<ResetPassword> {
            override fun onResponse(call: Call<ResetPassword>, response: Response<ResetPassword>) {
                if (response.isSuccessful){
                    _resetPassword.value = response.body()
                }
                else{
                    Log.e("onFailure", "onFailure: Code Salah")
                }
            }
            override fun onFailure(call: Call<ResetPassword>, t: Throwable) {
                Log.e("onFailure", "onFailure : ${t.message}")
            }

        })
    }
}