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
import com.aerotech.flytix.databinding.FragmentDataPemesanBinding
import com.aerotech.flytix.model.user.DataUserProfilePutItem
import com.aerotech.flytix.viewmodel.ProfileViewModel
import com.aerotech.flytix.viewmodel.SearchViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DataPemesan : Fragment() {

    private lateinit var binding: FragmentDataPemesanBinding
    private lateinit var userVm: ProfileViewModel
    private lateinit var pref: SharedPreferences

    //    private var flightTicketOneTrip = FlightTicketOneTrip()
//    private var flightTicketRoundTrip = FlightTicketRoundTrip()
    lateinit var searchViewModel: SearchViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        searchViewModel = ViewModelProvider(this)[SearchViewModel::class.java]
        userVm = ViewModelProvider(this).get(ProfileViewModel::class.java)
        pref = requireContext().getSharedPreferences("LOGIN", Context.MODE_PRIVATE)

        binding = FragmentDataPemesanBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getdataPemesan()
        btnSimpan()
    }

    fun getdataPemesan(): DataUserProfilePutItem {
        val token = pref.getString("token", "").toString()
        val email = binding.etEmail.text.toString()
        val noHp = binding.etEmail.text.toString()
        val fullname = binding.etEmail.text.toString()
        val username = binding.etEmail.text.toString()
        userVm.getDataProfile(token)
        userVm.authLiveDataUserProfile.observe(viewLifecycleOwner) {
            Log.d("Profile", "email : ${it!!.user.email}")
            binding.etEmail.setText(it.user.email)
            binding.etNoHp.setText(it.user.phoneNumber)
            binding.etnamapemesan.setText(it.user.fullName)
            binding.etUsername.setText(it.user.username)
        }
        return DataUserProfilePutItem(email, noHp, fullname, username)
    }

    fun btnSimpan() {
        binding.btnSimpanPemesan.setOnClickListener {
            searchViewModel.getValueTripOneway().observe(viewLifecycleOwner) {
                val id_ticket_go = arguments?.getInt("id_ticket_go")
                val bund = Bundle()
                if (id_ticket_go != null) {
                    bund.putInt("id_ticket_go", id_ticket_go)
                }
                if (it == false) {
//                    val getListSeatPassenger = arguments?.getIntArray("DATA_LIST_NUM_SEAT")
//                    bund.putSerializable("DATA_PEMESAN", getdataPemesan())
//                    bund.putIntArray("DATA_LIST_NUM_SEAT", getListSeatPassenger)
                    findNavController().navigate(R.id.action_dataPemesan_to_dataPenumpang, bund)
                } else {
                    val departureDate = arguments?.getString("departure_date")
                    val departureCity = arguments?.getString("departure_city")
                    val destinationCity = arguments?.getString("destination_city")
                    val returnDate = arguments?.getString("return_date")
                    val id_ticket_back = arguments?.getInt("id_ticket_back")

                    if (id_ticket_back != 0) {
                        if (id_ticket_back != null) {
                            bund.putInt("id_ticket_back", id_ticket_back)
                        }
//                        findNavController().navigate(R.id.action_dataPemesan_to_dataPenumpangX, bund)
                    } else {
//                        bund.putString("departureDate", departureDate)
//                        bund.putString("departureCity", departureCity)
//                        bund.putString("destinationCity", destinationCity)
//                        bund.putString("returnDate", returnDate)
//                        findNavController().navigate(R.id.resultSearch, bund)
                    }
                }
            }
        }
    }
}