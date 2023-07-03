package com.aerotech.flytix.view.home.search

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.aerotech.flytix.databinding.FragmentDataPenumpangBinding
import com.aerotech.flytix.model.books.Seat
import com.aerotech.flytix.model.penumpang.Penumpang
import com.aerotech.flytix.model.penumpang.PenumpangRequest
import com.aerotech.flytix.model.penumpang.ticket
import com.aerotech.flytix.view.adapter.DataPenumpangAdapter
import com.aerotech.flytix.viewmodel.BiodataViewModel
import com.aerotech.flytix.viewmodel.PenumpangViewModel
import com.aerotech.flytix.viewmodel.SearchViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class DataPenumpang : Fragment() {

    private lateinit var binding: FragmentDataPenumpangBinding
    private val searchViewModel: SearchViewModel by viewModels()
    private val penumpangViewModel: PenumpangViewModel by activityViewModels()
    private lateinit var penumpangAdapter: DataPenumpangAdapter
    private val biodataViewModel: BiodataViewModel by viewModels()
    private lateinit var sharedPref: SharedPreferences
//    private val biodataViewModel:BiodataViewModel by viewModels()
//    private val loginViewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDataPenumpangBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initDewasaAdapter()
        binding.btnLanjut.setOnClickListener {
//            val departureDate = arguments?.getString("TanggalKeberangkatan")
//            val departureCity = arguments?.getString("KotaKeberangkatan")
//            val destinationCity = arguments?.getString("KotaDestinasi")
//            val returnDate = arguments?.getString("TanggalKembali")
//
            val idticketDep = arguments?.getInt("id_ticket_go")
            val dataList = biodataViewModel.getDataList()
            val passenger: ArrayList<ticket> = ArrayList()
            val seats: ArrayList<Seat> = ArrayList()
            passenger.add(ticket(idticketDep!!))
            seats.add(Seat("1"))

            val penumpangData = PenumpangRequest(passenger, dataList, seats)
            sharedPref = requireContext().getSharedPreferences("LOGIN", Context.MODE_PRIVATE)
            val tokenUser = sharedPref.getString("token", "")!!
            biodataViewModel.biodataPenumpang(penumpangData!!, tokenUser)
            biodataViewModel.getBiodataPenumpangResponse.observe(viewLifecycleOwner) {
                if (it.status == "success") {
                    Toast.makeText(
                        requireContext(),
                        "Berhasil Menambahkan data penumpang",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    private fun initDewasaAdapter() {
        val listPenumpang: ArrayList<Penumpang> = ArrayList()
        searchViewModel.getJumlahPenumpangDewasa().observe(viewLifecycleOwner) {
            if (it != null) {
                for (i in 1..it.toInt()) {
                    listPenumpang.add(Penumpang("Dewasa $i", "Dewasa"))
                }
            }
        }

        searchViewModel.getJumlahPenumpangAnak().observe(viewLifecycleOwner) {
            if (it != null) {
                //get Penumpang Anak
                for (i in 1..it.toInt()) {
                    listPenumpang.add(Penumpang("Anak $i", "Anak"))
                }
            }
        }
        searchViewModel.getJumlahPenumpangBayi().observe(viewLifecycleOwner) {
            if (it != null) {
                for (i in 1..it.toInt()) {
                    listPenumpang.add(Penumpang("Bayi $i", "Bayi"))
                }
            }
        }
        binding.rvBiodataPenumpang.apply {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            penumpangAdapter = DataPenumpangAdapter(listPenumpang)
            adapter = penumpangAdapter
            penumpangAdapter.setOnItemClickListener(object :
                DataPenumpangAdapter.OnItemClickListener {
                override fun onItemClick(position: Int) {
                    Toast.makeText(
                        requireContext(),
                        "posisi card ini $position",
                        Toast.LENGTH_SHORT
                    ).show()
                    val bundle = Bundle()
                    val name = listPenumpang[position].penumpang
                    val role = listPenumpang[position].role
                    bundle.putInt("index", position)
                    bundle.putString("penumpang", name)
                    bundle.putString("role", role)
                    val penumpangDetail = DatapenumpangDetail()
                    penumpangDetail.arguments = bundle
                    penumpangDetail.show(parentFragmentManager, penumpangDetail.tag)
                }
            })
            isNestedScrollingEnabled = false
            setHasFixedSize(true)
        }

    }
}