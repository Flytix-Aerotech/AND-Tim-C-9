package com.aerotech.flytix.view.home.search

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.aerotech.flytix.R
import com.aerotech.flytix.databinding.FragmentDetailOwBinding
import com.aerotech.flytix.viewmodel.FlightViewModel
import com.aerotech.flytix.viewmodel.SearchViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailOw : Fragment() {
    lateinit var binding: FragmentDetailOwBinding
    private lateinit var searchViewModel: SearchViewModel
    private lateinit var flightViewModel: FlightViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        searchViewModel = ViewModelProvider(this)[SearchViewModel::class.java]
        binding = FragmentDetailOwBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        flightViewModel = ViewModelProvider(this)[FlightViewModel::class.java]
        getDetail()
        booking()
        super.onViewCreated(view, savedInstanceState)
    }

    private fun getDetail() {
        var id = arguments?.getInt("id_ticket_go")
        val id_back = arguments?.getInt("id_ticket_back")

        if (id_back != 0) {
            id = id_back
        }

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
                        binding.tvTotalPembayaran.text = it.data.price.toString() + "/pax"
                    }
                }
            }
        }
    }

    private fun booking() {
        binding.btnBooking.setOnClickListener {
            searchViewModel.getValueTripOneway().observe(viewLifecycleOwner) {
                val id_ticket_go = arguments?.getInt("id_ticket_go")
                val bund = Bundle()
                if (id_ticket_go != null) {
                    bund.putInt("id_ticket_go", id_ticket_go)
                    Log.e("DetailPenerbanganOw", "ticket dengan id: $id_ticket_go")
                }
                if (it == false) {
                    findNavController().navigate(R.id.action_detailOw_to_dataPemesan, bund)
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
                        findNavController().navigate(R.id.action_detailOw_to_dataPemesan, bund)
                    } else {
                        bund.putString("departureDate", departureDate)
                        bund.putString("departureCity", departureCity)
                        bund.putString("destinationCity", destinationCity)
                        bund.putString("returnDate", returnDate)
                        findNavController().navigate(R.id.pencarianTicketOw, bund)
                    }
                }
            }
        }
    }
}