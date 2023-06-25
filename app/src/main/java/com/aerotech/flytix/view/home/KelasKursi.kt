package com.aerotech.flytix.view.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aerotech.flytix.databinding.FragmentKelasKursiBinding
import com.aerotech.flytix.model.ticket.DataKelasKursi
import com.aerotech.flytix.view.adapter.KelasKursiAdapter
import com.aerotech.flytix.viewmodel.SearchViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class KelasKursi : BottomSheetDialogFragment(), KelasKursiAdapter.ItemSelectionListener {

    private lateinit var recyclerView: RecyclerView
    private lateinit var searchViewModel: SearchViewModel
    private var mList = ArrayList<DataKelasKursi>()
    private lateinit var adapterList: KelasKursiAdapter
    lateinit var selectedKelas: String
    private lateinit var binding: FragmentKelasKursiBinding
    internal var listener: kelasKursiListener? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        searchViewModel = ViewModelProvider(this)[SearchViewModel::class.java]
        binding = FragmentKelasKursiBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.icClosebottomsheetKelasKursi.setOnClickListener {
            dismiss()
        }
        recyclerView = binding.rvKelaskursi
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(context)
        addDataToList()
        adapterList = KelasKursiAdapter(mList, this)
        recyclerView.adapter = adapterList
    }


    private fun addDataToList() {
        mList.add(DataKelasKursi("Business"))
        mList.add(DataKelasKursi("Economy"))
        mList.add(DataKelasKursi("First"))
        mList.add(DataKelasKursi("Premium"))
    }

    interface kelasKursiListener {
        fun pilihKelasKursi(kelas: String)
    }

    override fun onItemSelected(selectedItem: DataKelasKursi) {
        selectedKelas = selectedItem.kelasKursi
        listener?.pilihKelasKursi(selectedKelas)
        searchViewModel.getKelasKursi().observe(viewLifecycleOwner) {
            searchViewModel.simpanKelasKursi(selectedKelas)
        }
        dismiss()
    }
}