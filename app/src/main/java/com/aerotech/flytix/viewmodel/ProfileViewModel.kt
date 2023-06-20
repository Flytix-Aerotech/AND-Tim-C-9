package com.aerotech.flytix.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.aerotech.flytix.model.DataUserProfilePutItem
import com.aerotech.flytix.model.DataUserResponse
import com.aerotech.flytix.network.ApiService
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(private val Client: ApiService) : ViewModel() {
    private var mlivedataupdateprofile: MutableLiveData<DataUserResponse> = MutableLiveData()
    val livedataupdateprofile: LiveData<DataUserResponse> get() = mlivedataupdateprofile
    fun updateprofile(token : String, updateprofile:DataUserProfilePutItem){
        Client.putupdateprofile("Bearer $token",updateprofile).enqueue(object : Callback<DataUserResponse>{
            override fun onResponse(call: Call<DataUserResponse>, response: Response<DataUserResponse>) {
                if (response.isSuccessful) {
                    mlivedataupdateprofile.postValue(response.body())
                } else {
                    Log.e("UpdateProfile", "Gagal Update")
                }
            }

            override fun onFailure(call: Call<DataUserResponse>, t: Throwable) {
                Log.e("UpdateProfile", "Gagal Update")
            }

        })
    }

    private val authUserProfile = MutableLiveData<DataUserResponse?>()
    val authLiveDataUserProfile: LiveData<DataUserResponse?> = authUserProfile

    fun getDataProfile(token: String) {
        Client.getprofile("Bearer $token").enqueue(object : Callback<DataUserResponse> {
            override fun onResponse(
                call: Call<DataUserResponse>,
                response: Response<DataUserResponse>
            ) {
                // Handle the response here
                if (response.isSuccessful) {
                    authUserProfile.postValue(response.body())

                } else {
                    Log.e("getDataProfile", "Cannot get data")
                }
            }

            override fun onFailure(call: Call<DataUserResponse>, t: Throwable) {
                Log.e("getDataProfile", "Cannot get data")
            }
        })
    }
}