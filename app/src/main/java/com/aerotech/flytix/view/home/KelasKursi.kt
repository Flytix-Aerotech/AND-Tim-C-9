package com.aerotech.flytix.view.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.aerotech.flytix.databinding.FragmentKelasKursiBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class KelasKursi : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentKelasKursiBinding
    internal var listener: kelasKursiListener? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentKelasKursiBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.icClosebottomsheetKelasKursi.setOnClickListener {
            dismiss()
        }
        binding.cvKelasEconomy.setOnClickListener {
            listener?.pilihKelasKursi(binding.tvEconomy.text.toString())
            dismiss()
        }
        binding.cvKelasBusiness.setOnClickListener {
            listener?.pilihKelasKursi(binding.tvBusiness.text.toString())
            dismiss()
        }
        binding.cvKelasFirst.setOnClickListener {
            listener?.pilihKelasKursi(binding.tvFirst.text.toString())
            dismiss()
        }
        binding.cvKelasQuiet.setOnClickListener {
            listener?.pilihKelasKursi(binding.tvQuiet.text.toString())
            dismiss()
        }
    }


    interface kelasKursiListener {
        fun pilihKelasKursi(kelas:String)
    }
}