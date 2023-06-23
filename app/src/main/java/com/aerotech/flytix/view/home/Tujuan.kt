package com.aerotech.flytix.view.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aerotech.flytix.databinding.FragmentTujuanBinding
import com.aerotech.flytix.model.ticket.DataKota
import com.aerotech.flytix.view.adapter.KotaAdapter
import com.aerotech.flytix.viewmodel.KotaViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint
import java.util.Locale

@AndroidEntryPoint
class Tujuan : BottomSheetDialogFragment(), KotaAdapter.ItemSelectionListener {

    private lateinit var recyclerView: RecyclerView
    private lateinit var searchView: SearchView
    private var mList = ArrayList<DataKota>()
    private lateinit var adapterList: KotaAdapter
    private lateinit var binding: FragmentTujuanBinding
    lateinit var kotaViewModel: KotaViewModel
    internal var listener: getItemkotaTujuanListener? = null
    lateinit var selectedKota: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentTujuanBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //kotaViewModel = ViewModelProvider(this).get(KotaViewModel::class.java)
        binding.icClosebottomsheettujuan.setOnClickListener {
            dismiss()
        }
        recyclerView = binding.rvTujuan
        searchView = binding.etSearchtujuan

        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(context)
        addDataToList()
        adapterList = KotaAdapter(mList, this)
        recyclerView.adapter = adapterList
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(textsearch: String?): Boolean {
                getSearchList(textsearch)
                return true
            }
        })
    }

    private fun getSearchList(query: String?) {
        if (query != null) {
            val filteredList = if (query.isBlank()){
                mList
            }else{
                mList.filter { item->
                    item.namaKota.lowercase(Locale.ROOT).contains(query.lowercase(Locale.ROOT))
                }
            }
            if (filteredList.isEmpty()) {
                Toast.makeText(context, "Kota Belum terdaftar", Toast.LENGTH_SHORT).show()
            } else {
                adapterList.setSearchList(filteredList)
            }
        }
    }

    private fun addDataToList() {
        mList.add(DataKota("Ambon"))
        mList.add(DataKota("Bandung"))
        mList.add(DataKota("Bali"))
        mList.add(DataKota("Denpasar"))
        mList.add(DataKota("Jakarta"))
        mList.add(DataKota("Malang"))
        mList.add(DataKota("Palembang"))
        mList.add(DataKota("Padang"))
        mList.add(DataKota("Papua"))
    }

    override fun onItemSelected(selectedItem: DataKota) {
        selectedKota = selectedItem.namaKota
        listener?.getItemKotaTujuan(selectedKota)
        dismiss()
    }

    interface getItemkotaTujuanListener {
        fun getItemKotaTujuan(kota: String)
    }
//        binding.icClearsearchview.setOnClickListener {
//            val searchText = binding.etSearch.text.toString()
//            getSearch(searchText)
//        }
    //}


//    fun getSearch(kota: String?) {
//        if (kota != null) {
//            kotaViewModel.getkotabySearch(kota)
//            kotaViewModel.kotaList.observe(viewLifecycleOwner) {
//                for (i in it){
//                    if (i.namaKota.lowercase(Locale.ROOT).contains(kota)){
//                        it.
//                    }
//                }
//                if (it != null) {
//                    showSearchLocation(it)
//                } else{
//                    Toast.makeText(context, "Kota Tidak Ada",Toast.LENGTH_SHORT).show()
//                }
//            }
//        } else {
//            kotaViewModel.getkota()
//            kotaViewModel.kotaList.observe(viewLifecycleOwner) {
//                if (it != null) {
//                    showLocation(it)
//                }
//            }
//        }
//    }

//    fun showSearchLocation(data: List<DataKota>) {
//        val searchAdapter = KotaAdapter(data, this)
//        binding.rvDarimana.adapter = searchAdapter
//        binding.rvDarimana.adapter
//        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
//        binding.rvDarimana.layoutManager = layoutManager
//    }
//
//    fun showLocation(data: List<DataKota>) {
//        val searchAdapter = KotaAdapter(data, this)
//        binding.rvDarimana.adapter = searchAdapter
//        binding.rvDarimana.adapter
//        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
//        binding.rvDarimana.layoutManager = layoutManager
//    }

}