package com.aerotech.flytix.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.aerotech.flytix.data.MainRepository
import com.aerotech.flytix.data.SearchDataStore
import com.aerotech.flytix.network.ApiService
import javax.inject.Inject

class HomeViewmodel @Inject constructor(
    private val client: ApiService,
    private val repository: MainRepository,
    private val pref: SearchDataStore,
    application: Application
) : AndroidViewModel(application) {

}