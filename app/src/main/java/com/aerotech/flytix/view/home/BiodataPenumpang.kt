package com.aerotech.flytix.view.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.aerotech.flytix.R
import com.aerotech.flytix.databinding.FragmentBiodataPenumpangBinding
import com.aerotech.flytix.model.passengers.Data
import com.aerotech.flytix.view.adapter.BiodataPenumpangAdapter

class BiodataPenumpang : Fragment() {
    lateinit var binding: FragmentBiodataPenumpangBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentBiodataPenumpangBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Contoh data penumpang
        val listPenumpang = listOf<Data>(

        )

        // Membuat objek adapter dan mengatur ke RecyclerView
        val adapter = BiodataPenumpangAdapter(listPenumpang)
        binding.rvBiodataPenumpang.adapter = adapter

    }


}