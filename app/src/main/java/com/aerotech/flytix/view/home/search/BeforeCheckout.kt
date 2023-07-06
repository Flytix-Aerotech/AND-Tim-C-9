package com.aerotech.flytix.view.home.search

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import com.aerotech.flytix.databinding.FragmentBeforeCheckoutBinding
import com.aerotech.flytix.model.books.Seat
import com.aerotech.flytix.model.penumpang.PenumpangRequest
import com.aerotech.flytix.model.penumpang.ticket
import com.aerotech.flytix.view.adapter.DataPenumpangAdapter
import com.aerotech.flytix.viewmodel.BiodataViewModel
import com.aerotech.flytix.viewmodel.FlightViewModel
import com.aerotech.flytix.viewmodel.SearchViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BeforeCheckout : Fragment() {
    lateinit var binding: FragmentBeforeCheckoutBinding
    private var isClicked = false
    private lateinit var searchViewModel: SearchViewModel
    private lateinit var flightViewModel: FlightViewModel
    lateinit var bund: Bundle
    private lateinit var penumpangAdapter: DataPenumpangAdapter
    private val biodataViewModel: BiodataViewModel by activityViewModels()
    private lateinit var sharedPref: SharedPreferences


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        searchViewModel = ViewModelProvider(this)[SearchViewModel::class.java]
        flightViewModel = ViewModelProvider(this)[FlightViewModel::class.java]
        bund = Bundle()
        sharedPref = requireContext().getSharedPreferences("LOGIN", Context.MODE_PRIVATE)
        binding = FragmentBeforeCheckoutBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getDetail()
        booking()
//        getDataDataTicket()
        super.onViewCreated(view, savedInstanceState)
    }

    private fun getDetail() {
        var id = sharedPref.getInt("id_ticket_go", 0)
        val id_back = arguments?.getInt("id_ticket_back")

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
//                        var hargaTiketPergi = it.data.price
//                        binding.tvTotalPembayaran.text = "$hargaTiketPergi/pax"
                        val hargaTiketPergi = sharedPref.getInt("HargaTiketPergi", 0)
                        val jumlahPenumpang = sharedPref.getString("jumlahPenumpang", "")!!
                        val totalbayar = hargaTiketPergi * jumlahPenumpang.toInt()
                        binding.tvTotalPembayaran.text = "Rp $totalbayar"
                        searchViewModel.getJumlahPenumpangDewasa().observe(viewLifecycleOwner) {
                            if (it != null) {
                                binding.txtAdult.text = "$it Dewasa"
                                var hargaDewasa = it.toInt() * hargaTiketPergi
                                binding.priceadult.text = hargaDewasa.toString()
                            }
                        }
                        searchViewModel.getJumlahPenumpangBayi().observe(viewLifecycleOwner) {
                            if (it != null) {
                                binding.txtBaby.text = "$it Bayi"
                                var hargaBayi = it.toInt() * hargaTiketPergi
                                binding.pricebaby.text = hargaBayi.toString()
                            }
                        }
                        searchViewModel.getJumlahPenumpangAnak().observe(viewLifecycleOwner) {
                            if (it != null) {
                                binding.txtKids.text = "$it Anak"
                                var hargaAnak = it.toInt() * hargaTiketPergi
                                binding.pricekids.text = hargaAnak.toString()
                            }
                        }
                    }
                }
            }
        }
    }

    private fun booking() {
        binding.btnLanjutpembayaran.setOnClickListener {
            searchViewModel.getValueTripOneway().observe(viewLifecycleOwner) {
                if (it == false) {
                    val emailUser = sharedPref.getString("emailUser", "")!!
                    val phoneUser = sharedPref.getString("telephoneUser", "")!!
                    val jumlahPenumpang = sharedPref.getString("jumlahPenumpang", "")!!
                    val fullnameuser = sharedPref.getString("fullnameUser", "")!!
                    val idticketDep = sharedPref.getInt("id_ticket_go", 0)
                    val totalBayar = sharedPref.getInt("totalBayar", 0)
                    val dataList = biodataViewModel.getDataList()
                    val seats: ArrayList<Seat> = ArrayList()
                    val tokenUser = sharedPref.getString("token", "")!!

                    for (i in 1..jumlahPenumpang.toInt()) {
                        val seat = Seat("$i A")
                        seats.add(seat)
                    }
                    val penumpangData = PenumpangRequest(
                        ticket(
                            emailUser,
                            fullnameuser,
                            phoneUser,
                            idticketDep,
                            totalBayar
                        ), dataList, seats
                    )
                    biodataViewModel.biodataPenumpang(
                        penumpangData,
                        tokenUser,
                        idticketDep.toString(),
                        jumlahPenumpang
                    )
                    biodataViewModel.getBiodataPenumpangResponse.observe(viewLifecycleOwner) { checkout ->
                        if (checkout.status == "success") {
                            Toast.makeText(
                                requireContext(),
                                "Berhasil Memesan Ticket",
                                Toast.LENGTH_SHORT
                            ).show()
//                        findNavController().navigate(R.id.action_dataPenumpang_to_beforeCheckout)
                        }
                    }
                }
            }

//            searchViewModel.getValueTripOneway().observe(viewLifecycleOwner){
//
//
//                val bund = Bundle()
//                if (idticketDep != null) {
//                    bund.putInt("id_oneway", idticketDep)
//                }
//                if (it == true){
//
////                    findNavController().navigate(R.id.action_detail_to_dataPemesan, bund)
//                }
//                else {
//                    val departureDate = arguments?.getString("departure_date")
//                    val departureCity = arguments?.getString("departure_city")
//                    val destinationCity = arguments?.getString("destination_city")
//                    val returnDate = arguments?.getString("return_date")
//                    val id_ticket_back = arguments?.getInt("id_ticket_back")
//                    if (id_ticket_back != 0){
//                        if (id_ticket_back != null) {
//                            bund.putInt("id_ticket_back", id_ticket_back)
//                        }
//                        findNavController().navigate(R.id.action_detail_to_dataPemesan, bund)
//                    } else {
//                        bund.putString("departureDate", departureDate)
//                        bund.putString("departureCity", departureCity)
//                        bund.putString("destinationCity", destinationCity)
//                        bund.putString("returnDate", returnDate)
////                        findNavController().navigate(R.id.resultSearch, bund)
//                    }
//                }
        }
    }

    override fun onStart() {
        super.onStart()
        val dataList = biodataViewModel.getDataList()
        Log.d("dataListCheckOut", "Penumpang : $dataList")
    }
}
