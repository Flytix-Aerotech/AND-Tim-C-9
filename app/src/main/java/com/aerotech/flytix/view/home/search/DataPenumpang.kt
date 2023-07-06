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
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.aerotech.flytix.R
import com.aerotech.flytix.databinding.FragmentDataPenumpangBinding
import com.aerotech.flytix.model.penumpang.Penumpang
import com.aerotech.flytix.view.adapter.DataPenumpangAdapter
import com.aerotech.flytix.viewmodel.BiodataViewModel
import com.aerotech.flytix.viewmodel.ProfileViewModel
import com.aerotech.flytix.viewmodel.SearchViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class DataPenumpang : Fragment() {

    private lateinit var binding: FragmentDataPenumpangBinding
    private val searchViewModel: SearchViewModel by viewModels()
    private val profileViewModel: ProfileViewModel by viewModels()
    private lateinit var penumpangAdapter: DataPenumpangAdapter
    private val biodataViewModel: BiodataViewModel by activityViewModels()
    private lateinit var sharedPref: SharedPreferences
    lateinit var bundle: Bundle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        sharedPref = requireContext().getSharedPreferences("LOGIN", Context.MODE_PRIVATE)
        bundle = Bundle()
        binding = FragmentDataPenumpangBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initDewasaAdapter()
        binding.btnLanjut.setOnClickListener {
            val idticketDep = arguments?.getInt("id_ticket_go")!!
            val HargaTiketPergi = arguments?.getInt("HargaTiketPergi")!!
            val jumlahPenumpang = sharedPref.getString("jumlahPenumpang", "")!!
            val totalBayar = HargaTiketPergi * jumlahPenumpang.toInt()
            Toast.makeText(
                requireContext(),
                "Berhasil Menambahkan data penumpang",
                Toast.LENGTH_SHORT
            ).show()
            val dataList = biodataViewModel.getDataList()
            val sharedpref = sharedPref.edit()
            sharedpref.putInt("HargaTiketPergi", HargaTiketPergi)
            sharedpref.putInt("totalBayar", totalBayar)
            sharedpref.putInt("id_ticket_go", idticketDep)
            sharedpref.apply()
            Log.e("HargaTiketPergi-penum", "Harga: $HargaTiketPergi")
            Log.e("jumlahPenumpang-penum", "jumlahPenumpang: $jumlahPenumpang")
            Log.d("dataListPenumpang", "Penumpang : $dataList")
            Log.e("HargatotalBayar-penum", "totalBayar: $totalBayar")
            findNavController().navigate(R.id.action_dataPenumpang_to_beforeCheckout)
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