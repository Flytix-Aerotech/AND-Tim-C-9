package com.aerotech.flytix.viewmodel

import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.aerotech.flytix.model.*
import com.aerotech.flytix.network.ApiService
import com.aerotech.flytix.view.Login
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject
@HiltViewModel

//    class UserViewModel @Inject constructor(private val Client: ApiService): ViewModel() {
//        var livedataUser: MutableLiveData<UsersData> = MutableLiveData()
//        val dataLoginUser: LiveData<UsersData> get() = livedataUser
//
//        fun UserLogin(request: RequestLogin){
//            Client.postLogin(request).enqueue(object :Callback<UsersData> {
//                override fun onResponse(call: Call<UsersData>, response: Response<UsersData>) {
//                        if (response.isSuccessful) {
//                            livedataUser.postValue(response.body())
//
//                        } else {
//
//                        }
//                }
//
//                override fun onFailure(call: Call<UsersData>, t: Throwable) {
//                    //livedataUser.postValue()
//                }
//
//            })
//        }


//    fun userLogin(email: String, password: String, callback: (String) -> Unit) {
//        Client.postLogin(email, password).enqueue(object : Callback<List<NewUser>> {
//            override fun onResponse(call: Call<List<NewUser>>, response: Response<List<NewUser>>) {
//                if (response.isSuccessful) {
//                    val token = response.body()?.token
//                    if (token != null) {
//                        callback(token)
//                    } else {
//                        callback("")
//                    }
//                } else {
//                    callback("")
//                }
//            }
//
//            override fun onFailure(call: Call<List<NewUser>>, t: Throwable) {
//                callback("")
//            }
//        })
//    }

//}

class UserViewModel @Inject constructor(private val client: ApiService) : ViewModel() {
    private val _livedataUser: MutableLiveData<DataUserResponse> = MutableLiveData()
    val livedataUser: LiveData<DataUserResponse> get() = _livedataUser

    fun userLogin(request: DataUserLoginItem, callback: (DataUserResponse) -> Unit) {
        client.postLogin(request).enqueue(object : Callback<DataUserResponse> {
            override fun onResponse(call: Call<DataUserResponse>, response: Response<DataUserResponse>) {
                if (response.isSuccessful) {
                    _livedataUser.value = response.body()
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