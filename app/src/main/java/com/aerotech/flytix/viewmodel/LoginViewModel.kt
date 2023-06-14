package com.aerotech.flytix.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.aerotech.flytix.model.DataUserLoginItem
import com.aerotech.flytix.model.DataUserResponse
import com.aerotech.flytix.model.User
import com.aerotech.flytix.network.ApiService
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject


@HiltViewModel
class LoginViewModel @Inject constructor(var Client: ApiService) : ViewModel() {


//    private val _userLogin = MutableLiveData<DataUserResponse>()
//    val userLogin : LiveData<DataUserResponse> = _userLogin
//
//    fun authLogin(login : DataUserLoginItem){
//        Client.postLogin(login).enqueue(object : Callback<DataUserResponse> {
//            override fun onResponse(call: Call<DataUserResponse>, response: Response<DataUserResponse>) {
//                if (response.isSuccessful){
//                    val data = response.body()
//                    if (data != null){
//                        _userLogin.postValue(data!!)
//                    }
//                }else{
//                    Log.e("Error: ", "onFailure : ${response.message()}")
//                }
//
//            }
//
//            override fun onFailure(call: Call<DataUserResponse>, t: Throwable) {
//                Log.d(ContentValues.TAG, "onFailure: ${t.message}")
//            }
//
//        })
//    }


//    fun authLogin(callback: Callback<User> ) {
//        Client.postUserLogin().enqueue(object : Callback<User> {
//            override fun onResponse(call: Call<User>, response: Response<User>) {
//                if (response.isSuccessful) {
//                    val data = response.body()
//                    if (data != null) {
//                        _userLogin.postValue(data!!)
//                    }
//                } else {
//                    Log.e("Error: ", "onFailure : ${response.message()}")
//                }
//
//            }
//
//            override fun onFailure(call: Call<User>, t: Throwable) {
//                Log.d(ContentValues.TAG, "onFailure: ${t.message}")
//            }
//
//        })
//    }

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
                    }
                else{
                    Log.e("Error: ", "onFailure : Login Error")
                }
            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                //keadaan gagal
            }
        })
    }

    //@HiltViewModel
//class LoginViewModel @Inject constructor(private val Client: ApiService) : ViewModel() {
    private var livedataLoginUser: MutableLiveData<DataUserResponse> = MutableLiveData()
    val dataPostLoginUser: LiveData<DataUserResponse> get() = livedataLoginUser
    fun userLogin(request: DataUserLoginItem) {
        Client.postLogin(request).enqueue(object : Callback<DataUserResponse> {
            override fun onResponse(
                call: Call<DataUserResponse>,
                response: Response<DataUserResponse>
            ) {
                if (response.isSuccessful) {
                    livedataLoginUser.value = response.body()
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
//    }