package com.aerotech.flytix.view.home.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.aerotech.flytix.R
import com.aerotech.flytix.databinding.FragmentDetailBinding
import com.aerotech.flytix.viewmodel.FlightViewModel
import com.aerotech.flytix.viewmodel.SearchViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class Detail : Fragment() {
    lateinit var binding: FragmentDetailBinding
    private var isClicked = false
    private lateinit var searchViewModel: SearchViewModel
    private lateinit var flightViewModel: FlightViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        searchViewModel = ViewModelProvider(this)[SearchViewModel::class.java]
        binding = FragmentDetailBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        flightViewModel = ViewModelProvider(this)[FlightViewModel::class.java]
        getDetail()
        booking()
//        getDataDataTicket()
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
                        binding.tvAsal.text = it.data!!.flight!!.departureLocation
                        binding.tvTujuan.text = it.data.flight!!.arrivalLocation
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
                        binding.tvTotalPembayaran.text = it.data.price.toString()
                    }
                }
            }
        }
    }

    private fun booking(){
        binding.btnBooking.setOnClickListener{
            searchViewModel.getValueTripOneway().observe(viewLifecycleOwner){
                val id_ticket_go = arguments?.getInt("id_ticket_go")
                val bund = Bundle()
                if (id_ticket_go != null) {
                    bund.putInt("id_oneway", id_ticket_go)
                }
                if (it == true){
                    findNavController().navigate(R.id.action_detail_to_dataPemesan, bund)
                }
                else {
                    val departureDate = arguments?.getString("departure_date")
                    val departureCity = arguments?.getString("departure_city")
                    val destinationCity = arguments?.getString("destination_city")
                    val returnDate = arguments?.getString("return_date")
                    val id_ticket_back = arguments?.getInt("id_ticket_back")
                    if (id_ticket_back != 0){
                        if (id_ticket_back != null) {
                            bund.putInt("id_ticket_back", id_ticket_back)
                        }
                        findNavController().navigate(R.id.action_detail_to_dataPemesan, bund)
                    } else {
                        bund.putString("departureDate", departureDate)
                        bund.putString("departureCity", departureCity)
                        bund.putString("destinationCity", destinationCity)
                        bund.putString("returnDate", returnDate)
//                        findNavController().navigate(R.id.resultSearch, bund)
                    }
                }
            }
        }
    }
}