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
import com.aerotech.flytix.databinding.FragmentPencarianTicketRtBinding
import com.aerotech.flytix.view.adapter.ResultSearchBackAdapter
import com.aerotech.flytix.viewmodel.FlightViewModel
import com.aerotech.flytix.viewmodel.SearchViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PencarianTicketRt : Fragment(), ResultSearchBackAdapter.ListSearchBackInterface {
    private lateinit var binding: FragmentPencarianTicketRtBinding
    private lateinit var pref: SharedPreferences
    private lateinit var searchViewModel: SearchViewModel
    private lateinit var flightViewModel: FlightViewModel
    private lateinit var TanggalKeberangkatan: String
    private lateinit var TanggalKembali:String
    private lateinit var KotaKeberangkatan: String
    private lateinit var KotaDestinasi: String
    private lateinit var KelasKursi: String
    private lateinit var bundle: Bundle


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        bundle = Bundle()
        searchViewModel = ViewModelProvider(this).get(SearchViewModel::class.java)
        flightViewModel = ViewModelProvider(this).get(FlightViewModel::class.java)
        binding = FragmentPencarianTicketRtBinding.inflate(inflater, container, false)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        getData()
        val idTicketPergi = arguments?.getInt("id_ticket_go").toString()
        getDepartureTicket(idTicketPergi)
    }

    fun getData() {
        TanggalKeberangkatan = arguments?.getString("TanggalKeberangkatan").toString()
        KotaKeberangkatan = arguments?.getString("KotaKeberangkatan").toString()
        KotaDestinasi = arguments?.getString("KotaDestinasi").toString()
        TanggalKembali = arguments?.getString("TanggalKembali").toString()
        KelasKursi = arguments?.getString("KelasKursi").toString()
        searchViewModel.getValueTripOneway().observe(viewLifecycleOwner) {
            if (it != false) {
                Log.d("tipe", it.toString())
                returnOnly()
            } else {
                Log.d("tipe", it.toString())
                //getRoundtrip()
            }
        }
    }


    private fun getDepartureTicket(idDeparture: String?) {
        flightViewModel.getFlightDetail(idDeparture!!.toInt())
        flightViewModel.flightDetail.observe(viewLifecycleOwner){it ->
            val dataItemTicket = it.data
            binding.tvJamKeberangkatan.text = dataItemTicket!!.flight.departureTime
            binding.tvJamSampai.text = dataItemTicket.flight.arrivalTime
            binding.tvKotaKeberangkatan.text = dataItemTicket.flight.departureLocation
            binding.tvKotaSampai.text = dataItemTicket.flight.arrivalLocation

            var hargaTiketPergi = dataItemTicket.price
            binding.tvHarga.text = hargaTiketPergi.toString()
            bundle.putInt("HargaTiketPergi", hargaTiketPergi)
            Log.d("Harga Tiket Pergi", "$hargaTiketPergi")
            binding.tvKotakeberangkatan.text = dataItemTicket.flight.departureLocation
            binding.tvKotadestinasi.text = dataItemTicket.flight.arrivalLocation
            binding.tvPesawat.text = "${dataItemTicket.flight.airline} - ${dataItemTicket.typeOfClass}"
        }
    }


    private fun returnOnly(
    ) {
        val adapter = ResultSearchBackAdapter(this)
        binding.apply {
            searchViewModel.getDataSearchTicketsRt(
                KotaKeberangkatan,
                KotaDestinasi,
                TanggalKembali,
                KelasKursi,
            )

            searchViewModel.getLiveDataSearchTicketRt().observe(viewLifecycleOwner) {
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
            rvTicketkepulangan.layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            rvTicketkepulangan.adapter = adapter
        }
    }




    override fun onItemClickBack(id: Int) {
        val idTicketPergi = arguments?.getInt("id_ticket_go").toString()
        bundle.putInt("id_ticket_back", id)
        bundle.putInt("id_ticket_go", idTicketPergi.toInt())
        bundle.putString("TanggalKeberangkatan", TanggalKeberangkatan)
        bundle.putString("KotaKeberangkatan", KotaKeberangkatan)
        bundle.putString("KotaDestinasi", KotaDestinasi)
        bundle.putString("TanggalKembali", TanggalKembali)


        searchViewModel.simpanidTicketKepulangan(id.toString())
        findNavController().navigate(R.id.action_pencarianTicketRt_to_detailRt, bundle)
    }
}