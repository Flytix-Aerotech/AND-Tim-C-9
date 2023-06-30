package com.aerotech.flytix.view.home.search

import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.aerotech.flytix.R
import com.aerotech.flytix.databinding.FragmentPencarianTicketRtDepBinding
import com.aerotech.flytix.view.adapter.ResultSearchAdapter
import com.aerotech.flytix.viewmodel.SearchViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PencarianTicketRtDep : Fragment(),ResultSearchAdapter.ListSearchGoInterface {
    private lateinit var binding: FragmentPencarianTicketRtDepBinding
    private lateinit var pref: SharedPreferences
    private lateinit var searchViewModel: SearchViewModel
    private lateinit var TanggalKeberangkatan: String
    var TanggalKembali: String? = null
    private lateinit var KotaKeberangkatan: String
    private lateinit var KotaDestinasi: String
    private lateinit var KelasKursi: String


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        searchViewModel = ViewModelProvider(this).get(SearchViewModel::class.java)
        binding = FragmentPencarianTicketRtDepBinding.inflate(inflater, container, false)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        getData()
    }

    fun getData() {
        TanggalKeberangkatan = arguments?.getString("TanggalKeberangkatan").toString()
        KotaKeberangkatan = arguments?.getString("KotaKeberangkatan").toString()
        KotaDestinasi = arguments?.getString("KotaDestinasi").toString()
        TanggalKembali = arguments?.getString("TanggalKembali").toString()
        KelasKursi = arguments?.getString("KelasKursi").toString()
        searchViewModel.getValueTripOneway().observe(viewLifecycleOwner) {
            if (it == true) {
                Log.d("tipe", it.toString())
                departureOnly()
            } else {
                Log.d("tipe", it.toString())
                //getRoundtrip()
            }
        }
        binding.tvKotakeberangkatan.text = KotaKeberangkatan
        binding.tvKotadestinasi.text = KotaDestinasi
    }


    private fun departureOnly(
    ) {
        val adapter = ResultSearchAdapter(this)
        binding.apply {
            searchViewModel.getDataSearchTicketsOw(
                KotaKeberangkatan,
                KotaDestinasi,
                TanggalKeberangkatan,
                KelasKursi,
            )

            searchViewModel.getLiveDataSearchTicketow().observe(viewLifecycleOwner) {
                if (it != null) {
                    adapter.setData(it)
                } else {
                    Snackbar.make(binding.root, "Data Gagal Dimuat", Snackbar.LENGTH_SHORT)
                        .setBackgroundTint(
                            ContextCompat.getColor(
                                requireContext(),
                                R.color.merah
                            )
                        )
                        .setTextColor(ContextCompat.getColor(requireContext(), R.color.white))
                        .show()
                }
            }
            rvDataFlight.layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            rvDataFlight.adapter = adapter
        }
    }

    override fun onItemClickGo(id: Int) {
        val bund = Bundle()
        bund.putInt("id_ticket_go", id)
        bund.putString("TanggalKeberangkatan", TanggalKeberangkatan)
        bund.putString("KotaKeberangkatan", KotaKeberangkatan)
        bund.putString("KotaDestinasi", KotaDestinasi)
        bund.putString("TanggalKembali", TanggalKembali)
        bund.putString("KelasKursi", KelasKursi)
        searchViewModel.simpanidTicketKeberangkatan(id.toString())
        findNavController().navigate(R.id.action_pencarianTicketRtDep_to_pencarianTicketRt, bund)
    }
}