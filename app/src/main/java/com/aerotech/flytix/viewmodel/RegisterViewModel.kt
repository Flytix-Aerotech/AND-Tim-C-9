package com.aerotech.flytix.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.aerotech.flytix.data.network.ApiService
import com.aerotech.flytix.model.NewUser
import com.aerotech.flytix.model.UsersData
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(private val Client: ApiService): ViewModel() {
    private var livedataUser: MutableLiveData<UsersData> = MutableLiveData()
    val dataPostUser: LiveData<UsersData> get() = livedataUser
    fun postUserRegister(dataUsers: NewUser) {
        //memakai callback yang retrofit
        Client.postRegister(dataUsers).enqueue(object : Callback<UsersData> {
            override fun onResponse(
                call: Call<UsersData>,
                response: Response<UsersData>
            ) {
                if (response.isSuccessful) {
                    val data = response.body()
                    livedataUser.postValue(data!!)
                } else {
                    Log.e("Error: ", response.body()!!.message)
                }
            }
            override fun onFailure(call: Call<UsersData>, t: Throwable) {
                Log.e("Error: ", "onFailure : ${t.message}")
            }
        })
    }
}