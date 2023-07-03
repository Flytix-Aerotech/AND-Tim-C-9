package com.aerotech.flytix.view.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aerotech.flytix.databinding.FragmentDarimanaBinding
import com.aerotech.flytix.model.ticket.DataKota
import com.aerotech.flytix.view.adapter.KotaAdapter
import com.aerotech.flytix.viewmodel.SearchViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint
import java.util.Locale

@AndroidEntryPoint
class Darimana : BottomSheetDialogFragment(), KotaAdapter.ItemSelectionListener {

    private lateinit var recyclerView: RecyclerView
    private lateinit var searchView: SearchView
    private lateinit var searchViewModel: SearchViewModel
    private var mList = ArrayList<DataKota>()
    private lateinit var adapterList: KotaAdapter
    private lateinit var binding: FragmentDarimanaBinding
    internal var listener: getItemkotaListener? = null
    lateinit var selectedKota: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        searchViewModel = ViewModelProvider(this)[SearchViewModel::class.java]
        binding = FragmentDarimanaBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //kotaViewModel = ViewModelProvider(this).get(KotaViewModel::class.java)
        binding.icClosebottomsheetdarimana.setOnClickListener {
            dismiss()
        }
        recyclerView = binding.rvDarimana
        searchView = binding.etSearch

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
            val filteredList = if (query.isBlank()) {
                mList
            } else {
                mList.filter { item ->
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
        mList.add(DataKota("Aceh"))
        mList.add(DataKota("Balikpapan"))
        mList.add(DataKota("Bandung"))
        mList.add(DataKota("Banjarmasin"))
        mList.add(DataKota("Batam"))
        mList.add(DataKota("Bekasi"))
        mList.add(DataKota("Bogor"))
        mList.add(DataKota("Denpasar"))
        mList.add(DataKota("Depok"))
        mList.add(DataKota("Jakarta"))
        mList.add(DataKota("Jambi"))
        mList.add(DataKota("Makassar"))
        mList.add(DataKota("Malang"))
        mList.add(DataKota("Manado"))
        mList.add(DataKota("Mataram"))
        mList.add(DataKota("Medan"))
        mList.add(DataKota("Padang"))
        mList.add(DataKota("Palembang"))
        mList.add(DataKota("Pekanbaru"))
        mList.add(DataKota("Pontianak"))
        mList.add(DataKota("Semarang"))
        mList.add(DataKota("Surabaya"))
        mList.add(DataKota("Surakarta"))
        mList.add(DataKota("Tangerang"))
        mList.add(DataKota("Yogyakarta"))
    }

    override fun onItemSelected(selectedItem: DataKota) {
        selectedKota = selectedItem.namaKota
        listener?.getItemKota(selectedKota)
        searchViewModel.getKotaKeberangkatan().observe(viewLifecycleOwner) {
            searchViewModel.simpanKotaKeberangkatan(selectedKota)
        }
        dismiss()
    }

    interface getItemkotaListener {
        fun getItemKota(kota: String)
    }
}