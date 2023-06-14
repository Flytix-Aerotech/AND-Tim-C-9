package com.aerotech.flytix.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.aerotech.flytix.model.DataUserLoginItem
import com.aerotech.flytix.model.DataUserResponse
import com.aerotech.flytix.network.ApiService
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val Client: ApiService) : ViewModel() {
    private var livedataLoginUser: MutableLiveData<DataUserResponse> = MutableLiveData()
    val dataPostLoginUser: LiveData<DataUserResponse> get() = livedataLoginUser
        fun userLogin(request: DataUserLoginItem, callback: (DataUserResponse) -> Unit) {
            Client.postLogin(request).enqueue(object : Callback<DataUserResponse> {
                override fun onResponse(call: Call<DataUserResponse>, response: Response<DataUserResponse>) {
                    if (response.isSuccessful) {
                        livedataLoginUser.value = response.body()
                        response.body()?.let {
                            callback(it)
                        }
                    } else {
                        //keadaan gagal
                    }
                }

                override fun onFailure(call: Call<DataUserResponse>, t: Throwable) {
                    //keadaan gagal
                }
            })
        }
    }