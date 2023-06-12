package com.aerotech.flytix.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.aerotech.flytix.model.NewUser
import com.aerotech.flytix.network.ApiService
import retrofit2.Callback
import javax.inject.Inject

class UserViewModel @Inject constructor(private val Client: ApiService): ViewModel() {
    private var livedataUser: MutableLiveData<List<NewUser>> = MutableLiveData()


}