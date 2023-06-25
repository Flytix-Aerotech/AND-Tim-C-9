package com.aerotech.flytix.view.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.aerotech.flytix.R
import com.aerotech.flytix.databinding.FragmentResultSearchBinding
import com.aerotech.flytix.view.adapter.ResultSearchAdapter
import com.aerotech.flytix.viewmodel.SearchViewModel
import com.aerotech.flytix.viewmodel.TicketViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ResultSearch : Fragment(), ResultSearchAdapter.ListSearchGoInterface {
    lateinit var binding: FragmentResultSearchBinding
    lateinit var ticketViewModel: TicketViewModel
    private lateinit var searchViewModel: SearchViewModel
    private lateinit var TanggalKeberangkatan: String
    var TanggalKembali: String? = null
    private lateinit var KotaKeberangkatan: String
    private lateinit var KotaDestinasi: String
    private lateinit var KelasKursi: String


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
        searchViewModel = ViewModelProvider(this)[SearchViewModel::class.java]

        getData()


//        getDataDataTicket()
        super.onViewCreated(view, savedInstanceState)
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
                getOneway()
            } else {
                Log.d("tipe", it.toString())
//                getRoundtrip()
            }
        }
    }

    fun getOneway() {
        binding.btnRoundtrip.visibility = View.INVISIBLE
        searchViewModel.getTanggalKeberangkatan().observe(viewLifecycleOwner) {
            if (it != null) {
                binding.date.text = it
            }
        }

        searchViewModel.getKotaKeberangkatan().observe(viewLifecycleOwner) {
            binding.tvKotakeberangkatan.text = it
        }

        searchViewModel.getKotaDestinasi().observe(viewLifecycleOwner) {
            binding.tvKotadestinasi.text = it
        }

        val adapter = ResultSearchAdapter(this)
        binding.apply {
            searchViewModel.getDataSearch(
                KotaKeberangkatan,
                KotaDestinasi,
                TanggalKeberangkatan,
                KelasKursi,
            )

            searchViewModel.getLiveDataSearch().observe(viewLifecycleOwner) {
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
            rvPost.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            rvPost.adapter = adapter
        }
    }


//    fun getDataDataTicket() {
//        ticketViewModel = ViewModelProvider(this).get(TicketViewModel::class.java)
//        ticketViewModel.getDataTicket()
//        ticketViewModel._userTicket.observe(viewLifecycleOwner) {
//            binding.rvPost.layoutManager =
//                LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
//            if (it != null) {
//                binding.rvPost.adapter = TicketAdapter(it)
//            }
//        }
//    }

    override fun onItemClickGo(id: Int) {
        var bund = Bundle()
        bund.putInt("id_ticket_go", id)
        bund.putString("TanggalKeberangkatan", TanggalKeberangkatan)
        bund.putString("KotaKeberangkatan", KotaKeberangkatan)
        bund.putString("KotaDestinasi", KotaDestinasi)
        bund.putString("TanggalKembali", TanggalKembali)

        findNavController().navigate(R.id.action_resultSearch_to_detail, bund)
    }
}