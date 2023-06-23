package com.aerotech.flytix.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.aerotech.flytix.model.ticket.DataKota


class KotaViewModel :ViewModel(){
    private val list = arrayListOf(
        DataKota("Bandung"),
        DataKota("Jakarta"),
        DataKota("Surabaya"),
        DataKota("Medan"),
        DataKota("Padang"),
        DataKota("Aceh"),
        DataKota("Ambon"),
        DataKota("Malang"),
        DataKota("Papua"),
        DataKota("Palembang"),
        DataKota("Yogyakarta"),
        DataKota("Bali")
    )

    val kotaList: MutableLiveData<List<DataKota>> = MutableLiveData(list)

    fun getkotabySearch(kotanya: String) {
        val kota = list
        kotaList.value = kota
    }

    fun getkota() {
        val kota = list
        kotaList.value = kota
    }
}