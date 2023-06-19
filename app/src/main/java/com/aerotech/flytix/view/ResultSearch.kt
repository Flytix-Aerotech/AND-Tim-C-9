package com.aerotech.flytix.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.aerotech.flytix.databinding.FragmentResultSearchBinding
import com.aerotech.flytix.view.adapter.TicketAdapter
import com.aerotech.flytix.viewmodel.TicketViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ResultSearch : Fragment() {
    lateinit var binding : FragmentResultSearchBinding
    lateinit var ticketViewModel :TicketViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentResultSearchBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getDataDataTicket()
    }
    fun getDataDataTicket(){
        ticketViewModel = ViewModelProvider(this).get(TicketViewModel::class.java)
        ticketViewModel.getDataTicket()
        ticketViewModel._userTicket.observe(viewLifecycleOwner,{
            binding.fragCon.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            if(it!= null){
            binding.fragCon.adapter = TicketAdapter(it)
            }
        })
    }
}