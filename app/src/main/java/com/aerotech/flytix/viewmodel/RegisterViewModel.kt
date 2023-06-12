package com.aerotech.flytix.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.aerotech.flytix.model.NewUser
import com.aerotech.flytix.network.ApiService
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(private val Client: ApiService): ViewModel(){
    private var livedataUser : MutableLiveData<List<NewUser>> = MutableLiveData()
    val dataPostUser: LiveData<List<NewUser>> get() = livedataUser
    fun postUserRegister(dataUsers:NewUser){
        //memakai callback yang retrofit
        Client.postRegister(dataUsers).enqueue(object : Callback<List<NewUser>> {
            override fun onResponse(
                call: Call<List<NewUser>>,
                response: Response<List<NewUser>>

            ) {
                if (response.isSuccessful){
                    livedataUser.postValue(response.body())
                }else{
                    livedataUser.postValue(emptyList())
                }
            }

            override fun onFailure(call: Call<List<NewUser>>, t: Throwable) {
                livedataUser.postValue(emptyList())
            }
        })
    }
}