package com.aerotech.flytix.view.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.aerotech.flytix.databinding.FragmentDarimanaBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class Darimana : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentDarimanaBinding
    internal var listener: DariManaListener? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentDarimanaBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.icClosebottomsheetdarimana.setOnClickListener {
            dismiss()
        }
    }

    interface DariManaListener {
        fun onSelectedDarimana(dari: String)
    }
}