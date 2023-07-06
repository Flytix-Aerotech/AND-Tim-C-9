package com.aerotech.flytix.viewmodel

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.aerotech.flytix.model.user.DataUserLoginItem
import com.aerotech.flytix.model.user.DataUserResponse
import com.aerotech.flytix.network.ApiService
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject


@HiltViewModel
class LoginViewModel @Inject constructor(var Client: ApiService) : ViewModel() {
    private val authUserLogin = MutableLiveData<DataUserResponse>()
    val authLiveDataUserLogin: LiveData<DataUserResponse> = authUserLogin
    private val liveLoadData = MutableLiveData<Boolean>()
    val loadData: LiveData<Boolean> = liveLoadData
    fun authLoginUser(login: DataUserLoginItem) {
        liveLoadData.value = true
        Client.postLoginUser(login).enqueue(object : Callback<DataUserResponse> {
            override fun onResponse(
                call: Call<DataUserResponse>,
                response: Response<DataUserResponse>
            ) {
                if (response.isSuccessful) {
                    val data = response.body()
                    if (data != null) {
                        liveLoadData.value = false
                        authUserLogin.postValue(data!!)
                        Log.e("Error: ", "onSuccess : ${response.message()}")
                    }
                } else {
                    liveLoadData.value = false
                    Log.e("Error: ", "onFailure : ${response.message()}")
                }

            }

            override fun onFailure(call: Call<DataUserResponse>, t: Throwable) {
                Log.d(ContentValues.TAG, "onFailure: ${t.message}")
            }
        })
    }
}
