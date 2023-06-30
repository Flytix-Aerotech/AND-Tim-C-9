package com.aerotech.flytix.viewmodel

import androidx.lifecycle.ViewModel
import com.dwiki.tiketku.model.penumpang.PenumpangData

class PenumpangViewModel : ViewModel() {
    private val dataList: MutableList<PenumpangData> = mutableListOf()

    fun addData(data: PenumpangData) {
        dataList.add(data)
    }

    fun getDataList(): List<PenumpangData> {
        return dataList
    }
}