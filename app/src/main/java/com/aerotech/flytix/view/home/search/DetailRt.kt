package com.aerotech.flytix.view.home.search

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.aerotech.flytix.R
import com.aerotech.flytix.databinding.FragmentDetailRtBinding
import com.aerotech.flytix.viewmodel.FlightViewModel
import com.aerotech.flytix.viewmodel.SearchViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailRt : Fragment() {
    lateinit var binding: FragmentDetailRtBinding
    private var isClicked = false
    private lateinit var searchViewModel: SearchViewModel
    private lateinit var flightViewModel: FlightViewModel
    lateinit var sharedPref:SharedPreferences
    lateinit var bund:Bundle
    var hargaPergi = 0
    var hargaPulang = 0


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        searchViewModel = ViewModelProvider(this)[SearchViewModel::class.java]
        bund = Bundle()
        binding = FragmentDetailRtBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        flightViewModel = ViewModelProvider(this)[FlightViewModel::class.java]
        getDetailDeparture()
        getDetailArrival()
        booking()
        super.onViewCreated(view, savedInstanceState)
    }

    fun getDetailDeparture() {
        var id = arguments?.getInt("id_ticket_go")
        if (id != null) {
            flightViewModel.getFlightDetail(id)
            flightViewModel.flightDetail.observe(viewLifecycleOwner) {
                binding.apply {
                    if (it != null) {
                        binding.tvAsal.text =
                            "${it.data!!.flight!!.departureLocation} -> ${it.data!!.flight!!.arrivalLocation}"
                        binding.tvJamkeberangkatan.text = it.data.flight.departureTime
                        binding.tvTanggalKeberangkatan.text = it.data.flight.departureDate
                        binding.tvBandaraAsal.text = it.data.airport!!.departureName
                        binding.tvTerminalbandara.text = " - " + it.data.airport.departureTerminal
                        binding.tvJenisPesawat.text = it.data.flight.airline
                        binding.tvKelaspesawat.text = " - " + it.data.typeOfClass
                        binding.tvKodePesawat.text = it.data.flight.flightNumber
                        binding.tvKedatangan.text = it.data.flight.arrivalTime
                        binding.tvTanggalKedatangan.text = it.data.flight.departureDate
                        binding.tvBandaraKedatangan.text = it.data.airport.arrivalName
                        hargaPergi = it.data.price
                        Log.d("Harga Tiket Pergi", "$hargaPergi")
                    }
                }
            }
        }
    }

    fun getDetailArrival() {
        var id = arguments?.getInt("id_ticket_back")
        if (id != null) {
            flightViewModel.getFlightDetailBack(id)
            flightViewModel.flightDetailBack.observe(viewLifecycleOwner) {
                binding.apply {
                    if (it != null) {
                        binding.tvAsalKepulangan.text =
                            "${it.data!!.flight!!.departureLocation} <- ${it.data!!.flight!!.arrivalLocation}"
                        binding.tvJamkepulangan.text = it.data.flight.departureTime
                        binding.tvTanggalKepulangan.text = it.data.flight.departureDate
                        binding.tvTerminalbandarakepulangan.text = it.data.airport!!.departureName
                        binding.tvTerminalbandarakepulangan.text =
                            " - " + it.data.airport.departureTerminal
                        binding.tvJenisPesawatkepulangan.text = it.data.flight.airline
                        binding.tvKelaspesawatkepulangan.text = " - " + it.data.typeOfClass
                        binding.tvKodePesawatkepulangan.text = it.data.flight.flightNumber
                        binding.tvKedatangankepulangan.text = it.data.flight.arrivalTime
                        binding.tvTanggalKedatangankepulangan.text = it.data.flight.departureDate
                        binding.tvBandaraKedatangankepulangan.text = it.data.airport.arrivalName
                        hargaPulang = it.data.price
                        var hargaTiketPergi = arguments?.getInt("HargaTiketPergi")
                        var total = hargaPulang + hargaTiketPergi!!
                        binding.tvTotalPembayaran.text = "${total}/Pax"
                        Log.d("Harga Tiket Pulang", "${hargaPulang}")
                        Log.d("Harga total Tiket", "${total}")
                    }
                }
            }
        }
    }


    private fun booking() {
        binding.btnBooking.setOnClickListener {
            sharedPref = requireContext().getSharedPreferences("LOGIN", Context.MODE_PRIVATE)
            if (sharedPref.getString("token", "").toString().isNotEmpty()) {
                if (findNavController().currentDestination!!.id == R.id.detailRt) {
                    searchViewModel.getValueTripOneway().observe(viewLifecycleOwner) {
                        val id_ticket_go = arguments?.getInt("id_ticket_go")
                        if (id_ticket_go != null) {
                            bund.putInt("id_ticket_go", id_ticket_go)
                            Log.e("DetailPenerbanganOw", "ticket dengan id: $id_ticket_go")
                        }
                        if (it == false) {
//                            findNavController().navigate(R.id.detailrt, bund)
                        } else {
                            val departureDate = arguments?.getString("TanggalKeberangkatan")
                            val departureCity = arguments?.getString("KotaKeberangkatan")
                            val destinationCity = arguments?.getString("KotaDestinasi")
                            val returnDate = arguments?.getString("TanggalKembali")
                            val id_ticket_back = arguments?.getInt("id_ticket_back")
                            if (id_ticket_back != 0) {
                                if (id_ticket_back != null) {
                                    bund.putInt("id_ticket_back", id_ticket_back)
                                }
//                                findNavController().navigate(
//                                    R.id.action_detailOw_to_dataPemesan,
//                                    bund
//                                )
                            } else {
                                bund.putString("departureDate", departureDate)
                                bund.putString("departureCity", departureCity)
                                bund.putString("destinationCity", destinationCity)
                                bund.putString("returnDate", returnDate)
//                                findNavController().navigate(R.id.pencarianTicketOw, bund)
                            }
                        }
                    }
                }
            } else {
                findNavController().navigate(R.id.checkLoginUser)
            }
        }
    }
}