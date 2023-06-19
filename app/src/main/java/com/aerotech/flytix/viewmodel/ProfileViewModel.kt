//package com.aerotech.flytix.viewmodel
//
//import androidx.lifecycle.LiveData
//import androidx.lifecycle.MutableLiveData
//import androidx.lifecycle.ViewModel
//import com.aerotech.flytix.model.DataUserResponse
//import com.aerotech.flytix.model.User
//import com.aerotech.flytix.network.ApiService
//import dagger.hilt.android.lifecycle.HiltViewModel
//import retrofit2.Call
//import retrofit2.Callback
//import retrofit2.Response
//import javax.inject.Inject
//
//@HiltViewModel
//class ProfileViewModel @Inject constructor(var Client: ApiService) : ViewModel() {
//    private val authUserProfile = MutableLiveData<DataUserResponse>()
//    val authLiveDataUserProfile : LiveData<DataUserResponse> = authUserProfile
//
//    fun getDataProfile(token: String, user : User){
//        Client.getProfile(token, user).enqueue(object : Callback<DataUserResponse> {
//            override fun onResponse(
//                call: Call<DataUserResponse>,
//                response: Response<DataUserResponse>
//            ) {
//                TODO("Not yet implemented")
//            }
//
//            override fun onFailure(call: Call<DataUserResponse>, t: Throwable) {
//                TODO("Not yet implemented")
//            }
//
//        })
//    }
//}

package com.aerotech.flytix.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.aerotech.flytix.model.DataUserResponse
import com.aerotech.flytix.model.User
import com.aerotech.flytix.model.updateprofile.PutDataUpdateProfile
import com.aerotech.flytix.network.ApiService
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(private val client: ApiService) : ViewModel() {
    private val authUserProfile = MutableLiveData<DataUserResponse?>()
    val authLiveDataUserProfile: LiveData<DataUserResponse?> = authUserProfile

    fun getDataProfile(token: String) {
        client.getProfile(token = "Bearer $token").enqueue(object : Callback<DataUserResponse> {
            override fun onResponse(
                call: Call<DataUserResponse>,
                response: Response<DataUserResponse>
            ) {
                // Handle the response here
                if (response.isSuccessful) {
                    authUserProfile.postValue(response.body())

                } else {
                    authUserProfile.postValue(null)
                }
            }

            override fun onFailure(call: Call<DataUserResponse>, t: Throwable) {
                authUserProfile.postValue(null)
            }
        })
    }

    private val _responseUpdateProfile = MutableLiveData<DataUserResponse?>()
    val responseUpdateProfile : LiveData<DataUserResponse?> = _responseUpdateProfile
    fun updateDataProfile(token: String, dataUser: PutDataUpdateProfile) {
        client.updateProfile(token = "Bearer $token", dataUser).enqueue(object : Callback<DataUserResponse> {
            override fun onResponse(
                call: Call<DataUserResponse>,
                response: Response<DataUserResponse>
            ) {
                // Handle the response here
                if (response.isSuccessful) {
                    authUserProfile.postValue(response.body())

                } else {
                    authUserProfile.postValue(null)
                }
            }

            override fun onFailure(call: Call<DataUserResponse>, t: Throwable) {
                authUserProfile.postValue(null)
            }
        })
    }

}
