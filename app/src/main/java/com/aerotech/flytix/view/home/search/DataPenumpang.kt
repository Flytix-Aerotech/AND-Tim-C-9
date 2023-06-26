package com.aerotech.flytix.view.home.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.aerotech.flytix.databinding.FragmentDataPenumpangBinding
import com.aerotech.flytix.viewmodel.FlightViewModel
import com.aerotech.flytix.viewmodel.SearchViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DataPenumpang : Fragment() {
    lateinit var binding: FragmentDataPenumpangBinding
    private var isClicked = false
    private lateinit var searchViewModel: SearchViewModel
    private lateinit var flightViewModel: FlightViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        searchViewModel = ViewModelProvider(this)[SearchViewModel::class.java]
        binding = FragmentDataPenumpangBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        flightViewModel = ViewModelProvider(this)[FlightViewModel::class.java]

//        getDataDataTicket()
        super.onViewCreated(view, savedInstanceState)
    }
}