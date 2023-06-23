package com.aerotech.flytix.viewmodel

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.aerotech.flytix.model.user.DataUserLoginItem
import com.aerotech.flytix.model.user.DataUserResponse
import com.aerotech.flytix.model.user.User
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

    private val _userLogin = MutableLiveData<User>()
    val livedatauserLogin: LiveData<User> = _userLogin
    fun authLogin() {
        Client.postUserLogin().enqueue(object : Callback<User> {
            override fun onResponse(
                call: Call<User>,
                response: Response<User>
            ) {
                if (response.isSuccessful) {
                    _userLogin.value = response.body()
                } else {
                    Log.e("Error: ", "onFailure : Login Error")
                }
            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                //keadaan gagal
            }
        })
    }

//    private val _userLoginDone = MutableLiveData<DataUserResponse>()
//    val livedatauserLoginDone: LiveData<DataUserResponse> = _userLoginDone
//    fun authLoginDone() {
//        Client.loginUser().enqueue(object : Callback<DataUserResponse> {
//            override fun onResponse(
//                call: Call<DataUserResponse>,
//                response: Response<DataUserResponse>
//            ) {
//                if (response.isSuccessful) {
//                    _userLoginDone.value = response.body()
//                } else {
//                    Log.e("Error: ", "onFailure : Login Error")
//                }
//            }
//
//            override fun onFailure(call: Call<DataUserResponse>, t: Throwable) {
//                //keadaan gagal
//            }
//        })
//    }
//
//    //@HiltViewModel
////class LoginViewModel @Inject constructor(private val Client: ApiService) : ViewModel() {
//    private var livedataLoginUser: MutableLiveData<DataUserResponse> = MutableLiveData()
//    val dataPostLoginUser: LiveData<DataUserResponse> get() = livedataLoginUser
//    fun userLogin(request: DataUserLoginItem) {
//        Client.postLogin(request).enqueue(object : Callback<DataUserResponse> {
//            override fun onResponse(
//                call: Call<DataUserResponse>,
//                response: Response<DataUserResponse>
//            ) {
//                if (response.isSuccessful) {
//                    livedataLoginUser.value = response.body()
//                } else {
//                    //keadaan gagal
//                }
//            }
//
//            override fun onFailure(call: Call<DataUserResponse>, t: Throwable) {
//                //keadaan gagal
//            }
//        })
//    }
//
//    private val _responselogin : MutableLiveData<DataUserResponse> = MutableLiveData()
//    val responselogin : LiveData<DataUserResponse> = _responselogin
//
//    fun postlogin(loginBody: DataUserLoginItem) {
//        Client.postLogin(loginBody).enqueue(object : Callback<DataUserResponse>{
//            override fun onResponse(call: Call<DataUserResponse>, response: Response<DataUserResponse>) {
//                if (response.isSuccessful) {
//                    _responselogin.value = response.body()
//
//                } else {
//                    Log.e("UserViewModel", "Cannot get data")
//                }
//            }
//
//            override fun onFailure(call: Call<DataUserResponse>, t: Throwable) {
//                Log.e("UserViewModel", "Cannot get data")
//            }
//
//        })
//
//
//    }
}

//    }